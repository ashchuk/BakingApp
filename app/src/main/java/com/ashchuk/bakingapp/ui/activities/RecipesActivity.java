package com.ashchuk.bakingapp.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.mvp.presenters.RecipesPresenter;
import com.ashchuk.bakingapp.mvp.views.RecipesView;
import com.ashchuk.bakingapp.ui.adapters.RecipesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends MvpAppCompatActivity implements RecipesView {

    @BindView(R.id.recipes_rv)
    RecyclerView recipesView;

    @InjectPresenter RecipesPresenter recipesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        recipesView.setAdapter(new RecipesAdapter(recipes));
        recipesView.setLayoutManager(new LinearLayoutManager(RecipesActivity.this));
    }

    @Override
    public void showErrorMessage(String message) {

    }
}
