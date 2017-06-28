package com.moovel.demo;

import com.moovel.demo.injection.DaggerDemoApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class DemoApplication extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerDemoApplicationComponent.builder().application(this).build();
    }
}
