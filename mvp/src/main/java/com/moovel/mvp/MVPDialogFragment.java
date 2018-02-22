package com.moovel.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.moovel.mvp.lifecycle.LifecycleObserver;

public abstract class MVPDialogFragment<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>>
        extends DialogFragment implements BaseMVP<VIEW, PRESENTER> {

    private final MVPAndroidDelegate<VIEW, PRESENTER> delegate;

    public MVPDialogFragment() {
        delegate = new MVPAndroidDelegate<>(this);
    }

    public void addLifecycleObserver(LifecycleObserver interceptor) {
        delegate.addLifecycleObserver(interceptor);
    }

    public void removeLifecycleObserver(LifecycleObserver interceptor) {
        delegate.removeLifecycleObserver(interceptor);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        //noinspection unchecked
        delegate.attachView((VIEW) this, getPresenter());
        delegate.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        delegate.onResume();
    }

    @Override
    public void onPause() {
        delegate.onPause();
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        delegate.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        delegate.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        delegate.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        delegate.onLowMemory();
    }

}

