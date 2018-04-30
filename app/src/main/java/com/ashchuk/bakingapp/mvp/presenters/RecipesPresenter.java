package com.ashchuk.bakingapp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.ashchuk.bakingapp.BakingApp;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.mvp.services.BakingAppService;
import com.ashchuk.bakingapp.mvp.views.RecipesView;
import com.ashchuk.bakingapp.ui.RecipesObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class RecipesPresenter extends MvpPresenter<RecipesView> {

    @Inject
    BakingAppService bakingAppService;

    public RecipesPresenter() {

        BakingApp.getAppComponent().inject(this);

        if (bakingAppService == null) return;

        Observer<List<Recipe>> observer = new RecipesObserver(getViewState());
        bakingAppService.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
