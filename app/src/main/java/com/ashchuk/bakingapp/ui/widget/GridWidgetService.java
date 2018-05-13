package com.ashchuk.bakingapp.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.data.BakingAppContentProvider;
import com.ashchuk.bakingapp.data.BakingAppContract;
import com.ashchuk.bakingapp.mvp.models.Ingredient;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.google.gson.Gson;

public class GridWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext());
    }

    public class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context mContext;
        private Cursor mCursor;

        GridRemoteViewsFactory(Context context) {
            this.mContext = context;
        }

        @Override
        public void onCreate() {}

        @Override
        public void onDataSetChanged() {
            if (mCursor != null) mCursor.close();

            mCursor = mContext.getContentResolver().query(
                    BakingAppContentProvider.BakingIngredients.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );
        }

        @Override
        public void onDestroy() {
            if (mCursor != null) {
                mCursor.close();
            }
        }

        @Override
        public int getCount() {
            return (mCursor != null) ? mCursor.getCount(): 0;
        }

        @Override
        public RemoteViews getViewAt(int i) {
            if (mCursor == null || mCursor.getCount() == 0) return null;

            mCursor.moveToPosition(i);
            int jsonIndex = mCursor.getColumnIndex(BakingAppContract.COLUMN_SERIALIZED_RECIPE);
            String json = mCursor.getString(jsonIndex);

            Recipe recipe = new Gson().fromJson(json, Recipe.class);

            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

            StringBuilder builder = new StringBuilder();

            for(Ingredient ingredient: recipe.getIngredients()){
                builder.append(ingredient.getIngredient())
                        .append(": ")
                        .append(ingredient.getQuantity())
                        .append(" ")
                        .append(ingredient.getMeasure())
                        .append("\r\n");
            }

            remoteViews.setTextViewText(R.id.widget_recipe_name_tv, recipe.getName());
            remoteViews.setViewVisibility(R.id.widget_recipe_name_tv, View.VISIBLE);

            remoteViews.setTextViewText(R.id.widget_recipe_detail_tv, builder.toString());
            remoteViews.setViewVisibility(R.id.widget_recipe_detail_tv, View.VISIBLE);

            Intent fillIntent = new Intent();
            remoteViews.setOnClickFillInIntent(R.id.widget_recipe_name_tv, fillIntent);
            remoteViews.setOnClickFillInIntent(R.id.widget_recipe_detail_tv, fillIntent);

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
