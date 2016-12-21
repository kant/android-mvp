package com.moovel.mvpbase.rx;

import com.moovel.mvpbase.BasePresenter;
import com.moovel.mvpbase.MVPBase;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class RxBasePresenter<V extends MVPBase.View> extends BasePresenter<V> {

    private final CompositeSubscription stopSubscriptions = new CompositeSubscription();
    private final CompositeSubscription destroySubscriptions = new CompositeSubscription();

    protected void untilOnStop(Subscription subscription) {
        stopSubscriptions.add(subscription);
    }

    protected void untilOnDestroy(Subscription subscription) {
        destroySubscriptions.add(subscription);
    }


    @Override
    public void onStop() {
        super.onStop();
        stopSubscriptions.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroySubscriptions.clear();
    }
}
