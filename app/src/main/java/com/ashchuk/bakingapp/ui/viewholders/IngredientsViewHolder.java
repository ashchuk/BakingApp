package com.ashchuk.bakingapp.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ashchuk.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ingredient_name_tv)
    public TextView ingredientNameTv;
    @BindView(R.id.ingredient_measure_tv)
    public TextView ingredientMeasureTv;
    @BindView(R.id.ingredient_quantity_tv)
    public TextView ingredientQuantityTv;

    public IngredientsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}