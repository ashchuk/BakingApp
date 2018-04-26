package com.ashchuk.bakingapp.di.components;

import android.content.Context;
import com.ashchuk.bakingapp.di.modules.ContextModule;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class})
public interface AppComponent {
    Context getContext();
}

