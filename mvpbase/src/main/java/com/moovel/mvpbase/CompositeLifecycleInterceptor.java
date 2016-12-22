package com.moovel.mvpbase;

import java.util.ArrayList;
import java.util.List;

final class CompositeLifecycleInterceptor implements LifecycleInterceptor {


    private final List<LifecycleInterceptor> plugins = new ArrayList<>();

    public void addLifecycleInterceptor(LifecycleInterceptor plugin) {
        plugins.add(plugin);
    }

    public void removeLifecyclePlugin(LifecycleInterceptor plugin) {
        plugins.remove(plugin);
    }

    @Override
    public void doOnCreate() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnCreate();
        }
    }

    @Override
    public void doOnStart() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnStart();
        }
    }

    @Override
    public void doOnResume() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnResume();
        }
    }

    @Override
    public void doOnPause() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnPause();
        }
    }

    @Override
    public void doOnStop() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnStop();
        }
    }

    @Override
    public void doOnDestroy() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnDestroy();
        }
    }
}
