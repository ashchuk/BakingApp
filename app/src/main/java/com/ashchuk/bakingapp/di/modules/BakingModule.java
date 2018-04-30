package com.ashchuk.bakingapp.di.modules;

import com.ashchuk.bakingapp.api.IBakingAppAPI;
import com.ashchuk.bakingapp.mvp.services.BakingAppService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class})
public class BakingModule {
    @Provides
    @Singleton
    public BakingAppService provideBakingAppService(IBakingAppAPI bakingAppAPI) {
        return new BakingAppService(bakingAppAPI);
    }
}