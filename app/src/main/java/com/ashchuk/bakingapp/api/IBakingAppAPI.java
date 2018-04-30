package com.ashchuk.bakingapp.api;

import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.tools.Constants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IBakingAppAPI {
    @GET(Constants.RECIPES_URL)
    Observable<List<Recipe>> getRecipes();
}
