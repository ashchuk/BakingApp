package com.ashchuk.bakingapp.mvp.services;

import com.ashchuk.bakingapp.api.IBakingAppAPI;
import com.ashchuk.bakingapp.mvp.models.Recipe;

import java.util.List;

import dagger.Provides;
import io.reactivex.Observable;

public class BakingAppService {
    private IBakingAppAPI bakingAppAPI;

    public BakingAppService(IBakingAppAPI bakingAppAPI) {
        this.bakingAppAPI = bakingAppAPI;
    }

    public Observable<List<Recipe>> getRecipes() {
        return bakingAppAPI.getRecipes();
    }
}

