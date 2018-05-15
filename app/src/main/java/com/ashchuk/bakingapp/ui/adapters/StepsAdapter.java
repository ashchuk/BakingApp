package com.ashchuk.bakingapp.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Ingredient;
import com.ashchuk.bakingapp.mvp.models.Step;
import com.ashchuk.bakingapp.ui.activities.RecipeDetailActivity;
import com.ashchuk.bakingapp.ui.activities.RecipeListActivity;
import com.ashchuk.bakingapp.ui.fragments.RecipeDetailFragment;
import com.ashchuk.bakingapp.ui.viewholders.StepsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.ashchuk.bakingapp.tools.Constants.INGREDIENTS_KEY;
import static com.ashchuk.bakingapp.tools.Constants.STEP_KEY;

public class StepsAdapter extends RecyclerView.Adapter<StepsViewHolder> {

    private final RecipeListActivity mParentActivity;
    private final List<Step> mValues;
    private final List<Ingredient> ingredients;
    private final boolean mTwoPane;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Step item = (Step) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(STEP_KEY, item);
                arguments.putParcelableArrayList(INGREDIENTS_KEY, (ArrayList<? extends Parcelable>) ingredients);
                RecipeDetailFragment fragment = new RecipeDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipe_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra(STEP_KEY, item);
                intent.putParcelableArrayListExtra(INGREDIENTS_KEY, (ArrayList<? extends Parcelable>) ingredients);
                context.startActivity(intent);
            }
        }
    };

    public StepsAdapter(RecipeListActivity parent,
                        List<Step> items,
                        boolean twoPane,
                        List<Ingredient> ingredients) {
        this.mValues = items;
        this.ingredients = ingredients;
        this.mParentActivity = parent;
        this.mTwoPane = twoPane;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_content, parent, false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StepsViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).getShortDescription());

        if (!TextUtils.isEmpty(mValues.get(position).getThumbnailURL()))
            Picasso
                    .get()
                    .load(mValues.get(position).getThumbnailURL())
                    .noFade().resize(60, 60)
                    .centerCrop()
                    .into(holder.stepThumbnailIV);

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
