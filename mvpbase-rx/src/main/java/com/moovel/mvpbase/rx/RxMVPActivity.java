package com.moovel.mvpbase.rx;

import com.moovel.mvpbase.DependencyGraphProvider;
import com.moovel.mvpbase.MVPActivity;
import com.moovel.mvpbase.MVPBase;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class RxMVPActivity<VIEW extends MVPBase.View,
        PRESENTER extends RxBasePresenter<VIEW>,
        COMPONENT,
        PROVIDER extends DependencyGraphProvider<COMPONENT>> extends MVPActivity<VIEW, PRESENTER, COMPONENT, PROVIDER> {

    private final CompositeDisposable stopSubscriptions = new CompositeDisposable();
    private final CompositeDisposable destroySubscriptions = new CompositeDisposable();

    protected void untilOnStop(Disposable disposable) {
        stopSubscriptions.add(disposable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopSubscriptions.clear();
    }

    protected void untilOnDestroy(Disposable disposable) {
        destroySubscriptions.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroySubscriptions.clear();
    }
}
