package com.ashchuk.bakingapp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.ui.viewholders.RecipesViewHolder;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesViewHolder> {

    private List<Recipe> recipes;
    private OnRecipeClickListener clickListener;
    private OnRecipeAddListener addListener;
    private OnRecipeRemoveListener removeListener;

    public RecipesAdapter(List<Recipe> recipes, OnRecipeClickListener clickListener,
                          OnRecipeAddListener addListener,
                          OnRecipeRemoveListener removeListener) {
        this.recipes = recipes;
        this.clickListener = clickListener;
        this.addListener = addListener;
        this.removeListener = removeListener;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);
        return new RecipesViewHolder(view, clickListener, addListener, removeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        holder.bindViewHolder(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}