package com.moovel.mvp;

import java.util.HashSet;
import java.util.Set;

final class CompositeLifecycleInterceptor implements LifecycleInterceptor {

    private final Set<LifecycleInterceptor> plugins = new HashSet<>();

    public void addLifecycleInterceptor(LifecycleInterceptor plugin) {
        plugins.add(plugin);
    }

    public void removeLifecycleInterceptor(LifecycleInterceptor plugin) {
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
