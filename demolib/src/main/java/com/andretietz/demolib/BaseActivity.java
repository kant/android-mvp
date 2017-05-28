package com.andretietz.demolib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.moovel.mvp.MVPActivity;
import com.moovel.mvp.MVPPresenter;
import com.moovel.mvp.MVPView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseActivity<V extends MVPView, P extends MVPPresenter<V>> extends MVPActivity<V, P> implements
        HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> injector;

    @Inject
    protected P presenter;

    @Override
    protected P getPresenter() {
        return presenter;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return injector;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }
}
