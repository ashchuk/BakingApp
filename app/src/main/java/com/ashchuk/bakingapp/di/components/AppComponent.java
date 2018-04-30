package com.ashchuk.bakingapp.di.components;

import android.content.Context;
import com.ashchuk.bakingapp.di.modules.ContextModule;
import com.ashchuk.bakingapp.mvp.presenters.*;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class})
public interface AppComponent {
    Context getContext();

    void inject(RecipeStepPresenter recipeStepPresenter);
    void inject(RecipeDetailPresenter recipeDetailPresenter);
    void inject(RecipeListPresenter recipeListPresenter);
    void inject(RecipesPresenter repositoriesPresenter);
}

