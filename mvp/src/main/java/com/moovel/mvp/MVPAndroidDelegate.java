package com.moovel.mvp;

import android.os.Bundle;

import com.moovel.mvp.lifecycle.LifecycleObserver;

import java.util.ListIterator;

final class MVPAndroidDelegate<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>> {
    private final CompositeLifecycleObserver observer = new CompositeLifecycleObserver();

    public MVPAndroidDelegate(Object view) {
        if (!(view instanceof MVPView)) {
            throw new IllegalStateException(
                    String.format("%s must implement a View!", view.getClass().getSimpleName()));
        }
    }

    public void attachView(VIEW view, PRESENTER presenter) {
        //Check for old presenterLifecycleObserver and remove before adding a new one
        removeOldPresenterLifecycleObserverIfNeeded();

        observer.addLifecycleObserver(new PresenterLifecycleObserver<>(view, presenter));
    }

    private void removeOldPresenterLifecycleObserverIfNeeded() {
        ListIterator<LifecycleObserver> iter = observer.observers.listIterator();
        while (iter.hasNext()) {
            if (iter.next() instanceof PresenterLifecycleObserver) {
                iter.remove();
                break;
            }
        }
    }

    public void addLifecycleObserver(LifecycleObserver interceptor) {
        observer.addLifecycleObserver(interceptor);
    }

    public void removeLifecycleObserver(LifecycleObserver interceptor) {
        observer.removeLifecycleObserver(interceptor);
    }

    protected void onCreate(Bundle inState) {
        observer.onCreate(inState);
    }

    protected void onStart() {
        observer.onStart();
    }

    protected void onResume() {
        observer.onResume();
    }

    protected void onPause() {
        observer.onPause();
    }

    protected void onStop() {
        observer.onStop();
    }

    protected void onDestroy() {
        observer.onDestroy();
    }

    protected void onSaveInstanceState(Bundle outState) {
        observer.onSaveInstanceState(outState);
    }

    protected void onLowMemory() {
        observer.onLowMemory();
    }

    protected void onDestroyView() {
        observer.onDestroyView();
    }
}
