package com.codringreen.receptionloading.di.component;

import android.app.Application;

import com.codringreen.receptionloading.CodrinGreenApplication;
import com.codringreen.receptionloading.di.modules.ActivityModule;
import com.codringreen.receptionloading.di.modules.ApiModule;
import com.codringreen.receptionloading.di.modules.AppModule;
import com.codringreen.receptionloading.di.modules.DBModule;
import com.codringreen.receptionloading.di.modules.RepoModule;
import com.codringreen.receptionloading.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, DBModule.class, ActivityModule.class, AppModule.class,
        ApiModule.class, RepoModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(CodrinGreenApplication application);

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Application application);
    }
}