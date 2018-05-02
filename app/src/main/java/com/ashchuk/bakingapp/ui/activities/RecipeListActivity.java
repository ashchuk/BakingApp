package com.ashchuk.bakingapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.mvp.views.RecipeListView;
import com.ashchuk.bakingapp.ui.adapters.StepsAdapter;

import java.util.Objects;

public class RecipeListActivity extends MvpAppCompatActivity implements RecipeListView {

    private boolean mTwoPane;
    private Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        recipe = Objects.requireNonNull(getIntent().getExtras()).getParcelable("recipe");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        if (findViewById(R.id.recipe_detail_container) != null) {
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.recipe_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new StepsAdapter(this, recipe.getSteps(), mTwoPane));
    }
}
