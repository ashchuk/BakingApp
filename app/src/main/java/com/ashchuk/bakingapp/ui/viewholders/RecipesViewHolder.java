package com.ashchuk.bakingapp.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.ui.adapters.OnRecipeClickListener;


public class RecipesViewHolder extends RecyclerView.ViewHolder {

    private TextView cakeNameTV;
    private Recipe recipe;

    public RecipesViewHolder(View itemView, OnRecipeClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(v -> listener.onClick(itemView, recipe));
        cakeNameTV = itemView.findViewById(R.id.cake_name_tv);
    }

    public void bindViewHolder(Recipe recipe) {
        this.recipe = recipe;
        cakeNameTV.setText(recipe.getName());
    }
}
