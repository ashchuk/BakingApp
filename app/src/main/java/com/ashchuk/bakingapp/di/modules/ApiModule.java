package com.ashchuk.bakingapp.di.modules;

import com.ashchuk.bakingapp.api.IBakingAppAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {RetrofitModule.class})
public class ApiModule {
    @Provides
    @Singleton
    public IBakingAppAPI provideIBakingAppAPI(Retrofit retrofit) { return retrofit.create(IBakingAppAPI.class); }
}
