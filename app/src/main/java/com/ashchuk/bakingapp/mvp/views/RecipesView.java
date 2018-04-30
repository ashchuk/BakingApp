package com.ashchuk.bakingapp.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.ashchuk.bakingapp.mvp.models.Recipe;

import java.util.List;

public interface RecipesView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showRecipes(List<Recipe> recipes);
}
