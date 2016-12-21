package com.moovel.mvpbase.rx;

import com.moovel.mvpbase.DependencyGraphProvider;
import com.moovel.mvpbase.MVPBase;
import com.moovel.mvpbase.MVPFragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class RxMVPFragment<VIEW extends MVPBase.View,
        PRESENTER extends RxBasePresenter<VIEW>,
        COMPONENT,
        PROVIDER extends DependencyGraphProvider<COMPONENT>> extends MVPFragment<VIEW, PRESENTER, COMPONENT, PROVIDER> {

    private final CompositeDisposable stopSubscriptions = new CompositeDisposable();
    private final CompositeDisposable destroySubscriptions = new CompositeDisposable();


    protected void untilOnStop(Disposable disposable) {
        stopSubscriptions.add(disposable);
    }

    protected void untilOnDestroy(Disposable disposable) {
        destroySubscriptions.add(disposable);
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
