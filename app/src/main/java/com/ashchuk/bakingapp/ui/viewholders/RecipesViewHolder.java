package com.ashchuk.bakingapp.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Recipe;

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    private TextView cakeNameTV;

    public RecipesViewHolder(View itemView) {
        super(itemView);
        cakeNameTV = itemView.findViewById(R.id.cake_name_tv);
    }

    public void bindViewHolder(Recipe recipe) {
        cakeNameTV.setText(recipe.getName());
    }
}
