package com.moovel.mvpbase.rx;

import com.moovel.mvpbase.BasePresenter;
import com.moovel.mvpbase.MVPBase;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class RxBasePresenter<V extends MVPBase.View> extends BasePresenter<V> {

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
