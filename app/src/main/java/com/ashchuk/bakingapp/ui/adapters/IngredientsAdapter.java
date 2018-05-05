package com.ashchuk.bakingapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Ingredient;
import com.ashchuk.bakingapp.ui.viewholders.IngredientsViewHolder;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {

    private final List<Ingredient> mIngredients;

    public IngredientsAdapter(List<Ingredient> items) {
        mIngredients = items;
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_content, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final IngredientsViewHolder holder, int position) {
        holder.mIdView.setText(mIngredients.get(position).getIngredient());
        holder.itemView.setTag(mIngredients.get(position));
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }
}