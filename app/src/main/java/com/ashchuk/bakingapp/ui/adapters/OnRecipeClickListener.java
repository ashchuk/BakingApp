package com.ashchuk.bakingapp.ui.adapters;

import android.os.Parcelable;
import android.view.View;

import com.ashchuk.bakingapp.mvp.models.Recipe;

public interface OnRecipeClickListener {
    void onClick(View v, Recipe recipe);
}
