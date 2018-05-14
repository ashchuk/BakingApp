package com.ashchuk.bakingapp.ui.activities;

import android.app.AlertDialog;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.ashchuk.bakingapp.BakingApp;
import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.data.BakingAppContentProvider;
import com.ashchuk.bakingapp.data.BakingAppContract;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.mvp.presenters.RecipesPresenter;
import com.ashchuk.bakingapp.mvp.views.RecipesView;
import com.ashchuk.bakingapp.ui.adapters.OnRecipeClickListener;
import com.ashchuk.bakingapp.ui.adapters.RecipesAdapter;
import com.ashchuk.bakingapp.ui.widget.BakingAppWidgetProvider;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends MvpAppCompatActivity implements RecipesView {

    @BindView(R.id.recipes_rv)
    RecyclerView recipesView;

    @InjectPresenter
    RecipesPresenter recipesPresenter;

    private AlertDialog errorDialog;

    private Integer spanCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);
        spanCount = this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 1 : 2;
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        recipesView.setAdapter(new RecipesAdapter(recipes,
                (v, recipe) -> {
                    Intent intent = new Intent(RecipesActivity.this, RecipeListActivity.class);
                    intent.putExtra("recipe", recipe);
                    startActivity(intent);
                },
                (v, recipe) -> {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(BakingAppContract.COLUMN_ID, recipe.getId());
                    contentValues.put(BakingAppContract.COLUMN_RECIPE_NAME, recipe.getName());
                    contentValues.put(BakingAppContract.COLUMN_SERIALIZED_RECIPE, new Gson().toJson(recipe));
                    getApplicationContext().getContentResolver()
                            .insert(BakingAppContentProvider.BakingIngredients.CONTENT_URI, contentValues);

                    updateWidget();
                },
                (v, recipe) -> {
                    Uri uri = BakingAppContentProvider.BakingIngredients.CONTENT_URI;
                    getApplicationContext().getContentResolver()
                            .delete(uri, "recipeName = ?", new String[]{"" + recipe.getName()});

                    updateWidget();
                }));
        recipesView.setLayoutManager(new GridLayoutManager(RecipesActivity.this, spanCount));
    }

    private void updateWidget() {
        Intent intent = new Intent(this, BakingAppWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = AppWidgetManager.getInstance(getApplication())
                .getAppWidgetIds(new ComponentName(getApplication(), BakingAppWidgetProvider.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
    }

    @Override
    public void showErrorMessage(String message) {

    }
}
