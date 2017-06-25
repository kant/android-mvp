package com.moovel.mvp;

import com.moovel.mvp.lifecycle.LifecycleObserver;

class MVPAndroidDelegate<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>> {
    private final CompositeLifecycleObserver observer = new CompositeLifecycleObserver();

    public MVPAndroidDelegate(Object view) {
        if (!(view instanceof MVPView)) {
            throw new IllegalStateException(
                    String.format("%s must implement a View!", view.getClass().getSimpleName()));
        }
    }

    public void attachView(VIEW view, PRESENTER presenter) {
        observer.addLifecycleObserver(new PresenterLifecycleObserver<>(view, presenter));
    }

    public void addLifecycleObserver(LifecycleObserver interceptor) {
        observer.addLifecycleObserver(interceptor);
    }

    public void removeLifecycleObserver(LifecycleObserver interceptor) {
        observer.removeLifecycleObserver(interceptor);
    }

    protected void onCreate() {
        observer.doOnCreate();
    }

    protected void onStart() {
        observer.doOnStart();
    }

    protected void onResume() {
        observer.doOnResume();
    }

    protected void onPause() {
        observer.doOnPause();
    }

    protected void onStop() {
        observer.doOnStop();
    }

    protected void onDestroy() {
        observer.doOnDestroy();
    }
}
