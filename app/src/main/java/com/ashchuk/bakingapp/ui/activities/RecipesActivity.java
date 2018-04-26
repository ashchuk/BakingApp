package com.ashchuk.bakingapp.ui.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.views.RecipesView;

public class RecipesActivity extends MvpAppCompatActivity implements RecipesView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
    }
}
