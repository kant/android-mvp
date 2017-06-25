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

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.moovel.mvp.lifecycle.LifecycleObserver;

/**
 * Every Fragment should extend the MVPFragment, which provides some mvp base functionalities
 */
public abstract class MVPFragment<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>>
        extends Fragment implements BaseMVP<VIEW, PRESENTER> {

    private final MVPAndroidDelegate<VIEW, PRESENTER> delegate;

    public MVPFragment() {
        delegate = new MVPAndroidDelegate<>(this);
    }

    public void addLifecycleObserver(LifecycleObserver interceptor) {
        delegate.addLifecycleObserver(interceptor);
    }

    public void removeLifecycleObserver(LifecycleObserver interceptor) {
        delegate.removeLifecycleObserver(interceptor);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //noinspection unchecked
        delegate.attachView((VIEW) this, getPresenter());
        delegate.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
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
}
