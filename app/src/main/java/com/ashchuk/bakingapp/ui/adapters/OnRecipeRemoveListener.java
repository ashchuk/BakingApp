package com.ashchuk.bakingapp.ui.adapters;

import android.view.View;

import com.ashchuk.bakingapp.mvp.models.Recipe;

public interface OnRecipeRemoveListener {
    void onClick(View v, Recipe recipe);
}
