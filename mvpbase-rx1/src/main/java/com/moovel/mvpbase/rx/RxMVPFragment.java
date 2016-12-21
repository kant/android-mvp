package com.moovel.mvpbase.rx;

import com.moovel.mvpbase.DependencyGraphProvider;
import com.moovel.mvpbase.MVPBase;
import com.moovel.mvpbase.MVPFragment;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class RxMVPFragment<VIEW extends MVPBase.View,
        PRESENTER extends RxBasePresenter<VIEW>,
        COMPONENT,
        PROVIDER extends DependencyGraphProvider<COMPONENT>> extends MVPFragment<VIEW, PRESENTER, COMPONENT, PROVIDER> {

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
