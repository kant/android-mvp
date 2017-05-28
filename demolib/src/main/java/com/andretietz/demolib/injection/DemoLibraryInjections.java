package com.andretietz.demolib.injection;

import com.andretietz.demolib.injection.scopes.ActivityScope;
import com.andretietz.demolib.screens.DemoLibraryActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DemoLibraryInjections {
    @ActivityScope
    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract DemoLibraryActivity injectIntoDemoActivity();
}
