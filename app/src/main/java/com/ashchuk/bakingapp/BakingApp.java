package com.ashchuk.bakingapp;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.ashchuk.bakingapp.di.components.AppComponent;
import com.ashchuk.bakingapp.di.components.DaggerAppComponent;
import com.ashchuk.bakingapp.di.modules.ContextModule;

public class BakingApp extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }


    @VisibleForTesting
    public static void setAppComponent(@NonNull AppComponent appComponent) {
        sAppComponent = appComponent;
    }

}
