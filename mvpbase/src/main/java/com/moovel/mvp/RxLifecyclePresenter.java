package com.moovel.mvp;

import rx.Subscription;

public abstract class RxLifecyclePresenter<V extends View> extends BasePresenter<V> {

    private final RxCleanupInterceptor lifecycleInterceptor = new RxCleanupInterceptor();

    public RxLifecyclePresenter() {
        addLifecycleInterceptor(lifecycleInterceptor);
    }

    public void untilOnPause(Subscription subscription) {
        lifecycleInterceptor.untilOnPause(subscription);
    }

    public void untilOnStop(Subscription subscription) {
        lifecycleInterceptor.untilOnStop(subscription);
    }

    public void untilOnDestroy(Subscription subscription) {
        lifecycleInterceptor.untilOnDestroy(subscription);
    }
}
