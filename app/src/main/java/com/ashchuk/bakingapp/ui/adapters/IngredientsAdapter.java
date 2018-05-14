package com.ashchuk.bakingapp.ui.adapters;

import android.support.annotation.NonNull;
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

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final IngredientsViewHolder holder, int position) {
        holder.ingredientNameTv.setText(mIngredients.get(position).getIngredient());
        holder.ingredientQuantityTv.setText("Quantity: " + Float.toString(mIngredients.get(position).getQuantity()));
        holder.ingredientMeasureTv.setText("Measure: " + mIngredients.get(position).getMeasure());
        holder.itemView.setTag(mIngredients.get(position));
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }
}