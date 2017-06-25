package com.andretietz.demolib.injection;

import android.app.Activity;

import com.andretietz.demolib.injection.scopes.ActivityScope;
import com.andretietz.demolib.screens.DemoLibraryActivity;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = DemoLibraryInjections.DemoLibraryActivitySubcomponent.class)
public abstract class DemoLibraryInjections {
//    @ActivityScope
//    @ContributesAndroidInjector(modules = {ActivityModule.class})
//    abstract DemoLibraryActivity injectIntoDemoActivity();

    @Binds
    @IntoMap
    @ActivityKey(DemoLibraryActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(
            DemoLibraryInjections.DemoLibraryActivitySubcomponent.Builder builder);

    @Subcomponent(modules = ActivityModule.class)
    @ActivityScope
    public interface DemoLibraryActivitySubcomponent extends AndroidInjector<DemoLibraryActivity> {

        @Subcomponent.Builder
        abstract class Builder extends AndroidInjector.Builder<DemoLibraryActivity> {
            protected abstract Builder provideActivityModule(ActivityModule module);

            @Override
            public void seedInstance(DemoLibraryActivity instance) {
                provideActivityModule(new ActivityModule(instance));
            }
        }
    }
}
