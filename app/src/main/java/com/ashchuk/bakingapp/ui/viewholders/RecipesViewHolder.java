package com.ashchuk.bakingapp.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.ui.adapters.OnRecipeAddListener;
import com.ashchuk.bakingapp.ui.adapters.OnRecipeClickListener;
import com.ashchuk.bakingapp.ui.adapters.OnRecipeRemoveListener;


public class RecipesViewHolder extends RecyclerView.ViewHolder {

    private TextView cakeNameTV;
    private Recipe recipe;

    public RecipesViewHolder(View itemView, OnRecipeClickListener clickListener,
                             OnRecipeAddListener addListener,
                             OnRecipeRemoveListener removeListener) {
        super(itemView);
        cakeNameTV = itemView.findViewById(R.id.cake_name_tv);
        ImageButton addRecipeButton = itemView.findViewById(R.id.add_recipe_button);
        ImageButton removeRecipeButton = itemView.findViewById(R.id.remove_recipe_button);

        itemView.setOnClickListener(v -> clickListener.onClick(itemView, recipe));
        addRecipeButton.setOnClickListener(v -> addListener.onClick(itemView, recipe));
        removeRecipeButton.setOnClickListener(v -> removeListener.onClick(itemView, recipe));
    }

    public void bindViewHolder(Recipe recipe) {
        this.recipe = recipe;
        cakeNameTV.setText(recipe.getName());
    }
}
