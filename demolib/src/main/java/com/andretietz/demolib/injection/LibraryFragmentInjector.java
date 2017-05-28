package com.andretietz.demolib.injection;

import com.andretietz.demolib.injection.scopes.FragmentScope;
import com.andretietz.demolib.screens.DemoLibraryFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LibraryFragmentInjector {
    @FragmentScope
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DemoLibraryFragment provideDemoLibraryFragment();
}
