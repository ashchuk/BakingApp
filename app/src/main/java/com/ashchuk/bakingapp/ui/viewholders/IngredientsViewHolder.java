package com.ashchuk.bakingapp.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ashchuk.bakingapp.R;

public class IngredientsViewHolder extends RecyclerView.ViewHolder {
    public TextView ingredientNameTv;
    public TextView ingredientMeasureTv;
    public TextView ingredientQuantityTv;

    public IngredientsViewHolder(View view) {
        super(view);
        ingredientNameTv = view.findViewById(R.id.ingredient_name_tv);
        ingredientMeasureTv = view.findViewById(R.id.ingredient_measure_tv);
        ingredientQuantityTv = view.findViewById(R.id.ingredient_quantity_tv);
    }
}