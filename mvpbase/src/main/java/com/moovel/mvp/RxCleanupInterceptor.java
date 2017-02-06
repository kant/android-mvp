package com.moovel.mvp;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public final class RxCleanupInterceptor extends LifecycleAdapter {

    public static final int PAUSE = 0;
    public static final int STOP = 1;
    public static final int DESTROY = 2;

    private final Map<Integer, CompositeSubscription> subscriptionMap = new HashMap<>(3);

    /**
     * auto unsubscribes from subscription on "onStop()"
     *
     * @param subscription to unsubscribe from
     */
    protected void untilOnStop(Subscription subscription) {
        addSubscription(STOP, subscription);
    }

    /**
     * auto unsubscribes from subscription on "onDestroy()"
     *
     * @param subscription to unsubscribe from
     */
    protected void untilOnDestroy(Subscription subscription) {
        addSubscription(DESTROY, subscription);
    }

    /**
     * auto unsubscribes from subscription on "onPause()"
     *
     * @param subscription to unsubscribe from
     */
    protected void untilOnPause(Subscription subscription) {
        addSubscription(PAUSE, subscription);
    }


    @Override
    public void doOnStop() {
        clearSubscription(STOP);
    }

    @Override
    public void doOnDestroy() {
        clearSubscription(DESTROY);
    }

    @Override
    public void doOnPause() {
        clearSubscription(PAUSE);
    }

    private void addSubscription(int idx, Subscription subscription) {
        CompositeSubscription idxSubsription = subscriptionMap.get(idx);
        if (idxSubsription == null) {
            idxSubsription = new CompositeSubscription();
            subscriptionMap.put(idx, idxSubsription);
        }
        idxSubsription.add(subscription);
    }

    private void clearSubscription(int idx) {
        CompositeSubscription idxSubsription = subscriptionMap.get(idx);
        if (idxSubsription == null) return;
        idxSubsription.clear();

    }
}
