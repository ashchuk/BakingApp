package com.ashchuk.bakingapp.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.ui.adapters.OnRecipeAddListener;
import com.ashchuk.bakingapp.ui.adapters.OnRecipeClickListener;
import com.ashchuk.bakingapp.ui.adapters.OnRecipeRemoveListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cake_name_tv)
    public TextView cakeNameTV;
    @BindView(R.id.cake_image_iv)
    public ImageView cakeImageIV;
    @BindView(R.id.add_recipe_button)
    public ImageButton addRecipeButton;
    @BindView(R.id.remove_recipe_button)
    public ImageButton removeRecipeButton;

    private Recipe recipe;

    public RecipesViewHolder(View itemView, OnRecipeClickListener clickListener,
                             OnRecipeAddListener addListener,
                             OnRecipeRemoveListener removeListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(v -> clickListener.onClick(itemView, recipe));
        addRecipeButton.setOnClickListener(v -> addListener.onClick(itemView, recipe));
        removeRecipeButton.setOnClickListener(v -> removeListener.onClick(itemView, recipe));
    }

    public void bindViewHolder(Recipe recipe) {
        this.recipe = recipe;
        cakeNameTV.setText(recipe.getName());

        if (!TextUtils.isEmpty(recipe.getImage()))
        Picasso
                .get()
                .load(recipe.getImage())
                .noFade().resize(60, 60)
                .centerCrop()
                .into(cakeImageIV);
    }
}
