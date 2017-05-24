package com.moovel.mvp.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.moovel.mvp.MVPActivity;
import com.moovel.mvp.MVPPresenter;
import com.moovel.mvp.MVPView;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<V extends MVPView, P extends MVPPresenter<V>> extends MVPActivity<V, P> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }
}
