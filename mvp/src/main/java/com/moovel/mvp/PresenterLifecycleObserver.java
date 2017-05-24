package com.moovel.mvp;

import com.moovel.mvp.lifecycle.LifecycleInterceptor;

class PresenterLifecycleObserver<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>> implements LifecycleInterceptor {

    private final PRESENTER presenter;
    private final VIEW view;

    PresenterLifecycleObserver(VIEW view, PRESENTER presenter) {
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public void doOnCreate() {
        presenter.onCreate();
    }

    @Override
    public void doOnStart() {
        presenter.attachView(view);
        presenter.onStart();
    }

    @Override
    public void doOnResume() {
        presenter.onResume();
    }

    @Override
    public void doOnPause() {
        presenter.onPause();
    }

    @Override
    public void doOnStop() {
        presenter.onStop();
        presenter.detachView();
    }

    @Override
    public void doOnDestroy() {
        presenter.onDestroy();
    }
}
