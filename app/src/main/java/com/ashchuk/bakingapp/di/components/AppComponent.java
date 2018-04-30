package com.ashchuk.bakingapp.di.components;

import android.content.Context;

import com.ashchuk.bakingapp.di.modules.BakingModule;
import com.ashchuk.bakingapp.di.modules.ContextModule;
import com.ashchuk.bakingapp.mvp.presenters.*;
import com.ashchuk.bakingapp.mvp.services.BakingAppService;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, BakingModule.class})
public interface AppComponent {
    Context getContext();
    BakingAppService getBakingAppService();

    void inject(RecipeStepPresenter recipeStepPresenter);
    void inject(RecipeDetailPresenter recipeDetailPresenter);
    void inject(RecipeListPresenter recipeListPresenter);
    void inject(RecipesPresenter recipesPresenter);
}

