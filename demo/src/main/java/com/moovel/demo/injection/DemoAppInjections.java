package com.moovel.demo.injection;


import android.app.Activity;

import com.andretietz.demolib.injection.ActivityModule;
import com.andretietz.demolib.injection.scopes.ActivityScope;
import com.moovel.demo.screens.DemoActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = DemoAppInjections.DemoActivitySubcomponent.class)
public abstract class DemoAppInjections {

//    @ActivityScope
//    @ContributesAndroidInjector(modules = ActivityModule.class)
//    abstract DemoActivity injectIntoDemoActivity();

    @Binds
    @IntoMap
    @ActivityKey(DemoActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(
            DemoActivitySubcomponent.Builder builder);

    @ActivityScope
    @Subcomponent(modules = ActivityModule.class)
    interface DemoActivitySubcomponent extends AndroidInjector<DemoActivity> {
        @Subcomponent.Builder
        abstract class Builder extends AndroidInjector.Builder<DemoActivity> {
            protected abstract Builder provideActivityModule(ActivityModule module);
            @Override
            public void seedInstance(DemoActivity instance) {
                provideActivityModule(new ActivityModule(instance));
            }
        }
    }
}
