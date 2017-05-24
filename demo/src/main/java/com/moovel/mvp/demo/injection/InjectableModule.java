package com.moovel.mvp.demo.injection;

import com.moovel.mvp.demo.screens.main.AwesomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Module defines which Activity, Fragments are injectable
 */
@Module
public abstract class InjectableModule {
    @ContributesAndroidInjector
    abstract AwesomeActivity provide();
}
