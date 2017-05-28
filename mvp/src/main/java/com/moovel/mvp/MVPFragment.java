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
import android.support.v4.app.Fragment;

import com.moovel.mvp.lifecycle.LifecycleObserver;

/**
 * Every Fragment should extend the MVPFragment, which provides some mvp base functionalities
 */
public abstract class MVPFragment<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>>
        extends Fragment {

    private final CompositeLifecycleObserver observer = new CompositeLifecycleObserver();

    public MVPFragment() {
        if (!(this instanceof MVPView)) {
            throw new IllegalStateException(
                    String.format("The Fragment \"%s\" must implement a View!", this.getClass().getSimpleName()));
        }
    }

    public void addLifecycleObserver(LifecycleObserver interceptor) {
        observer.addLifecycleObserver(interceptor);
    }

    public void removeLifecycleObserver(LifecycleObserver interceptor) {
        observer.removeLifecycleObserver(interceptor);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //noinspection unchecked
        observer.addLifecycleObserver(new PresenterLifecycleObserver<>((VIEW) this, getPresenter()));
        observer.doOnCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        observer.doOnStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        observer.doOnResume();
    }

    @Override
    public void onPause() {
        observer.doOnPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        observer.doOnStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        observer.doOnDestroy();
        super.onDestroy();
    }


    /**
     * @return the presenter
     */
    protected abstract PRESENTER getPresenter();

}
