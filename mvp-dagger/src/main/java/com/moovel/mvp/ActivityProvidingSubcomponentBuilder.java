package com.moovel.mvp;

import android.app.Activity;

import dagger.android.AndroidInjector;


/**
 * To simplify the approach of injecting the activity object.
 * <a href="https://stackoverflow.com/questions/43371863/dagger-2-10-android-subcomponents-and-builders">
 * How to Inject the Activity into modules.</a>
 *
 * @param <T> Activity which will be injected
 */
public abstract class ActivityProvidingSubcomponentBuilder<T extends Activity> extends AndroidInjector.Builder<T> {
    protected abstract ActivityProvidingSubcomponentBuilder provideActivityModule(ActivityProvidingModule module);

    @Override
    public void seedInstance(T instance) {
        provideActivityModule(new ActivityProvidingModule(instance));
    }
}
