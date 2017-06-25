package com.andretietz.demolib.injection;

import android.app.Activity;

import dagger.android.AndroidInjector;

public abstract class ActivityModuleSubcomponentBuilder<T extends Activity> extends AndroidInjector.Builder<T> {
    protected abstract ActivityModuleSubcomponentBuilder provideActivityModule(ActivityModule module);

    @Override
    public void seedInstance(T instance) {
        provideActivityModule(new ActivityModule(instance));
    }
}
