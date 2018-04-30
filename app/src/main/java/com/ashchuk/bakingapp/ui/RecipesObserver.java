package com.ashchuk.bakingapp.ui;

import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.mvp.views.RecipesView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RecipesObserver implements Observer<List<Recipe>> {

    private RecipesView recipesView;

    public RecipesObserver(RecipesView recipesView) {
        this.recipesView = recipesView;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(List<Recipe> recipes) {
        recipesView.showRecipes(recipes);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
