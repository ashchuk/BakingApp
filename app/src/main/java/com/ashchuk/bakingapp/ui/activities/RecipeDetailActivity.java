package com.ashchuk.bakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.mvp.views.RecipesView;
import com.ashchuk.bakingapp.ui.fragments.RecipeDetailFragment;

import java.util.List;

import static com.ashchuk.bakingapp.tools.Constants.INGREDIENTS_KEY;
import static com.ashchuk.bakingapp.tools.Constants.STEP_KEY;

public class RecipeDetailActivity extends MvpAppCompatActivity implements RecipesView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putParcelable(STEP_KEY, getIntent().getParcelableExtra(STEP_KEY));
            arguments.putParcelableArrayList(INGREDIENTS_KEY, getIntent().getParcelableArrayListExtra(INGREDIENTS_KEY));
            RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, RecipeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {

    }

    @Override
    public void showErrorMessage(String message) {

    }
}
