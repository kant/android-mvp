package com.moovel.mvp;

import android.app.Activity;

import dagger.android.AndroidInjector;

public abstract class ActivityProvidingSubcomponentBuilder<T extends Activity> extends AndroidInjector.Builder<T> {
    protected abstract ActivityProvidingSubcomponentBuilder provideActivityModule(ActivityProvidingModule module);

    @Override
    public void seedInstance(T instance) {
        provideActivityModule(new ActivityProvidingModule(instance));
    }
}
