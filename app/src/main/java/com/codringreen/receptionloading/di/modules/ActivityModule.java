package com.codringreen.receptionloading.di.modules;

import com.codringreen.receptionloading.view.activities.CreateFarmActivity;
import com.codringreen.receptionloading.view.activities.DashboardActivity;
import com.codringreen.receptionloading.view.activities.FarmActivity;
import com.codringreen.receptionloading.view.activities.FarmDataActivity;
import com.codringreen.receptionloading.view.activities.LoginActivity;
import com.codringreen.receptionloading.view.activities.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract SplashActivity contributeSplashActivity();

    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector
    abstract DashboardActivity contributeDashboardActivity();

    @ContributesAndroidInjector
    abstract FarmActivity contributeFarmActivity();

    @ContributesAndroidInjector
    abstract CreateFarmActivity contributeCreateFarmActivity();

    @ContributesAndroidInjector
    abstract FarmDataActivity contributeFarmDataActivity();
}