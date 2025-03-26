package com.codringreen.receptionloading;

import android.app.Application;

import com.codringreen.receptionloading.di.component.DaggerAppComponent;
import com.codringreen.receptionloading.helper.PreferenceManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class CodrinGreenApplication extends Application implements HasAndroidInjector {

    @Inject  // ✅ This tells Dagger how to provide an instance
    public CodrinGreenApplication() {
    }

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceManager.INSTANCE.createPreferences(this);
        // Initialize Dagger
        DaggerAppComponent.factory().create(this).inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
