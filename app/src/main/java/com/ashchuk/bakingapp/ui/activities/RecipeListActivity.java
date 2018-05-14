package com.ashchuk.bakingapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.ashchuk.bakingapp.R;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.mvp.views.RecipeListView;
import com.ashchuk.bakingapp.ui.adapters.StepsAdapter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ashchuk.bakingapp.tools.Constants.RECIPE_KEY;

public class RecipeListActivity extends MvpAppCompatActivity implements RecipeListView {

    private boolean mTwoPane;
    private Recipe recipe;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recipe_list)
    View recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        ButterKnife.bind(this);

        recipe = Objects.requireNonNull(getIntent().getExtras()).getParcelable(RECIPE_KEY);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.recipe_detail_container) != null)
            mTwoPane = true;

        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new StepsAdapter(this, recipe.getSteps(), mTwoPane, recipe.getIngredients()));
    }
}
