package com.moovel.mvpbase.rx;

import com.moovel.mvpbase.DependencyGraphProvider;
import com.moovel.mvpbase.MVPActivity;
import com.moovel.mvpbase.MVPBase;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class RxMVPActivity<VIEW extends MVPBase.View,
        PRESENTER extends RxBasePresenter<VIEW>,
        COMPONENT,
        PROVIDER extends DependencyGraphProvider<COMPONENT>> extends MVPActivity<VIEW, PRESENTER, COMPONENT, PROVIDER> {

    private final CompositeSubscription stopSubscriptions = new CompositeSubscription();
    private final CompositeSubscription destroySubscriptions = new CompositeSubscription();

    protected void untilOnStop(Subscription subscription) {
        stopSubscriptions.add(subscription);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopSubscriptions.clear();
    }

    protected void untilOnDestroy(Subscription subscription) {
        destroySubscriptions.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroySubscriptions.clear();
    }
}
