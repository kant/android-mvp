package com.moovel.mvpbase.demo.screens.main;

import com.moovel.mvpbase.LifecycleInterceptor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RxLifecycleInterceptor implements LifecycleInterceptor {

    private final CompositeDisposable pauseDisposable = new CompositeDisposable();
    private final CompositeDisposable stopDisposable = new CompositeDisposable();
    private final CompositeDisposable destroyDisposable = new CompositeDisposable();

    public void untilOnPause(Disposable disposable) {
        pauseDisposable.add(disposable);
    }

    public void untilOnStop(Disposable disposable) {
        stopDisposable.add(disposable);
    }

    public void untilOnDestroy(Disposable disposable) {
        destroyDisposable.add(disposable);
    }


    @Override
    public void doOnCreate() {

    }

    @Override
    public void doOnStart() {

    }

    @Override
    public void doOnResume() {

    }

    @Override
    public void doOnPause() {
        pauseDisposable.clear();
    }

    @Override
    public void doOnStop() {
        stopDisposable.clear();
    }

    @Override
    public void doOnDestroy() {
        destroyDisposable.clear();
    }


    private final SparseArray<CompositeSubscription> subscriptionMap = new SparseArray<>(3);

    /**
     * auto unsubscribes from subscription on "onStop()"
     *
     * @param subscription to unsubscribe from
     */
    protected void untilOnStop(Subscription subscription) {
        addSubscription(LifecycleSubscriptions.STOP, subscription);
    }

    /**
     * auto unsubscribes from subscription on "onDestroy()"
     *
     * @param subscription to unsubscribe from
     */
    protected void untilOnDestroy(Subscription subscription) {
        addSubscription(LifecycleSubscriptions.DESTROY, subscription);
    }

    /**
     * auto unsubscribes from subscription on "onPause()"
     *
     * @param subscription to unsubscribe from
     */
    protected void untilOnPause(Subscription subscription) {
        addSubscription(LifecycleSubscriptions.PAUSE, subscription);
    }


    @Override
    public void onStop() {
        super.onStop();
        clearSubscription(LifecycleSubscriptions.STOP);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearSubscription(LifecycleSubscriptions.DESTROY);
    }

    @Override
    public void onPause() {
        super.onPause();
        clearSubscription(LifecycleSubscriptions.PAUSE);
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
