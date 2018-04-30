package com.ashchuk.bakingapp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.ashchuk.bakingapp.BakingApp;
import com.ashchuk.bakingapp.mvp.models.Recipe;
import com.ashchuk.bakingapp.mvp.services.BakingAppService;
import com.ashchuk.bakingapp.mvp.views.RecipesView;

import org.reactivestreams.Subscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class RecipesPresenter extends MvpPresenter<RecipesView> {

    @Inject
    BakingAppService bakingAppService;

    public RecipesPresenter() {

        BakingApp.getAppComponent().inject(this);

        Subscriber<List<Recipe>> subscriber;
        Observer<List<Recipe>> observer = new Observer<List<Recipe>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Recipe> recipes) {
                getViewState().showRecipes(recipes);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        if (bakingAppService == null)
            return;

        bakingAppService.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


//        unsubscribeOnDestroy(disposable);
    }
}
