package com.moovel.mvp;

import timber.log.Timber;

public final class LifecycleLogger implements LifecycleInterceptor {

    private final String className;

    public LifecycleLogger(String className) {
        this.className = className;
    }

    @Override
    public void doOnCreate() {
        Timber.v("%s.onCreate", className);
    }

    @Override
    public void doOnStart() {
        Timber.v("%s.onStart", className);
    }

    @Override
    public void doOnResume() {
        Timber.v("%s.onResume", className);
    }

    @Override
    public void doOnPause() {
        Timber.v("%s.onPause", className);
    }

    @Override
    public void doOnStop() {
        Timber.v("%s.onStop", className);
    }

    @Override
    public void doOnDestroy() {
        Timber.v("%s.onDestroy", className);
    }
}
