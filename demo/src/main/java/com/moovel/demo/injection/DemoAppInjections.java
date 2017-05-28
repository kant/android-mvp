package com.moovel.demo.injection;


import com.andretietz.demolib.injection.ActivityModule;
import com.andretietz.demolib.injection.scopes.ActivityScope;
import com.moovel.demo.screens.DemoActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DemoAppInjections {

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    abstract DemoActivity injectIntoDemoActivity();
}
