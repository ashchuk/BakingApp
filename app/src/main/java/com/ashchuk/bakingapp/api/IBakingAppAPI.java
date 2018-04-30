package com.ashchuk.bakingapp.api;

import com.ashchuk.bakingapp.mvp.models.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IBakingAppAPI {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipes();
}
