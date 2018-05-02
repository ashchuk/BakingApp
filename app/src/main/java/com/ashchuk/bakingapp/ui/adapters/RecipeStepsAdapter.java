package com.ashchuk.bakingapp.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Step;
import com.ashchuk.bakingapp.ui.activities.RecipeDetailActivity;
import com.ashchuk.bakingapp.ui.activities.RecipeListActivity;
import com.ashchuk.bakingapp.ui.fragments.RecipeDetailFragment;
import com.ashchuk.bakingapp.ui.viewholders.RecipeStepsViewHolder;

import java.util.List;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsViewHolder> {

    private final RecipeListActivity mParentActivity;
    private final List<Step> mValues;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Step item = (Step) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putInt(RecipeDetailFragment.ARG_ITEM_ID, item.getId());
                arguments.putParcelable("step", item);
                RecipeDetailFragment fragment = new RecipeDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipe_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra(RecipeDetailFragment.ARG_ITEM_ID, item.getId());
                intent.putExtra("step", item);
                context.startActivity(intent);
            }
        }
    };

    public RecipeStepsAdapter(RecipeListActivity parent,
                              List<Step> items,
                              boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public RecipeStepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_content, parent, false);
        return new RecipeStepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipeStepsViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).getShortDescription());
        holder.mContentView.setText(mValues.get(position).getDescription());

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
