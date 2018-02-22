package com.moovel.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;

import com.moovel.mvp.lifecycle.LifecycleObserver;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class DaggerMVPDialogFragment<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>>
        extends AppCompatDialogFragment implements BaseMVP<VIEW, PRESENTER>, HasSupportFragmentInjector {

    private final MVPAndroidDelegate<VIEW, PRESENTER> delegate;

    @Inject PRESENTER presenter;
    @Inject DispatchingAndroidInjector<Fragment> childFragmentInjector;

    public DaggerMVPDialogFragment() {
        delegate = new MVPAndroidDelegate<>(this);
    }

    @Override
    public PRESENTER getPresenter() {
        return presenter;
    }

    public void addLifecycleObserver(LifecycleObserver interceptor) {
        delegate.addLifecycleObserver(interceptor);
    }

    public void removeLifecycleObserver(LifecycleObserver interceptor) {
        delegate.removeLifecycleObserver(interceptor);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        delegate.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        delegate.onDestroyView();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }
}
