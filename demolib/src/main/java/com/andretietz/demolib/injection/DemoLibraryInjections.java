package com.andretietz.demolib.injection;

import android.app.Activity;

import com.andretietz.demolib.injection.scopes.ActivityScope;
import com.andretietz.demolib.screens.DemoLibraryActivity;
import com.moovel.mvp.ActivityProvidingModule;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = DemoLibraryInjections.DemoLibraryActivitySubcomponent.class)
public abstract class DemoLibraryInjections {
    @Binds
    @IntoMap
    @ActivityKey(DemoLibraryActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(
            DemoLibraryActivitySubcomponent.Builder builder);

    @ActivityScope
    @Subcomponent(modules = {ActivityModule.class, ActivityProvidingModule.class})
    public interface DemoLibraryActivitySubcomponent extends AndroidInjector<DemoLibraryActivity> {
        @Subcomponent.Builder
        abstract class Builder extends AndroidInjector.Builder<DemoLibraryActivity> {
            protected abstract Builder provideActivityModule(ActivityProvidingModule module);
            @Override
            public void seedInstance(DemoLibraryActivity instance) {
                provideActivityModule(new ActivityProvidingModule(instance));
            }
        }
    }
}
