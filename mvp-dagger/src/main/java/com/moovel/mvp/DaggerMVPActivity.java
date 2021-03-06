/*
 * Copyright (c) 2017 moovel Group GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moovel.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.moovel.mvp.lifecycle.LifecycleObserver;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class DaggerMVPActivity<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>>
        extends DaggerAppCompatActivity implements BaseMVP<VIEW, PRESENTER> {

    private final MVPAndroidDelegate<VIEW, PRESENTER> delegate;

    @Inject
    PRESENTER presenter;

    public DaggerMVPActivity() {
        delegate = new MVPAndroidDelegate<>(this);
    }

    public void addLifecycleObserver(LifecycleObserver interceptor) {
        delegate.addLifecycleObserver(interceptor);
    }

    public void removeLifecycleObserver(LifecycleObserver interceptor) {
        delegate.removeLifecycleObserver(interceptor);
    }

    @Override
    public PRESENTER getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        delegate.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //noinspection unchecked
        delegate.attachView((VIEW) this, getPresenter());
        delegate.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        delegate.onResume();
    }

    @Override
    protected void onPause() {
        delegate.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        delegate.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        delegate.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        delegate.onLowMemory();
    }
}
