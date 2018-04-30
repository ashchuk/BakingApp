package com.ashchuk.bakingapp.ui.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.mvp.presenters.RecipesPresenter;
import com.ashchuk.bakingapp.mvp.views.RecipesView;

import java.util.List;

public class RecipesActivity extends MvpAppCompatActivity implements RecipesView {

    @InjectPresenter RecipesPresenter recipesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        List<Recipe> result = recipes;
    }
}
