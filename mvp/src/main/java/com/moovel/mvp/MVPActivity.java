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
import android.support.v7.app.AppCompatActivity;

import com.moovel.mvp.lifecycle.LifecycleObserver;

public abstract class MVPActivity<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>>
        extends AppCompatActivity {

    private final CompositeLifecycleObserver lifecycleInterceptor = new CompositeLifecycleObserver();

    public MVPActivity() {
        if (!(this instanceof MVPView)) {
            throw new IllegalStateException(
                    String.format("The Activity \"%s\" must implement a View!", this.getClass().getSimpleName()));
        }
    }

    public void addLifecycleInterceptor(LifecycleObserver interceptor) {
        lifecycleInterceptor.addLifecycleInterceptor(interceptor);
    }

    public void removeLifecycleInterceptor(LifecycleObserver interceptor) {
        lifecycleInterceptor.removeLifecycleInterceptor(interceptor);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //noinspection unchecked}
        lifecycleInterceptor.addLifecycleInterceptor(new PresenterLifecycleObserver<>((VIEW) this, getPresenter()));
        lifecycleInterceptor.doOnCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleInterceptor.doOnStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleInterceptor.doOnResume();
    }

    @Override
    protected void onPause() {
        lifecycleInterceptor.doOnPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleInterceptor.doOnStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        lifecycleInterceptor.doOnDestroy();
        super.onDestroy();
    }

    /**
     * use the component to inject your keeper and return it
     *
     * @return the keeper
     */
    protected abstract PRESENTER getPresenter();

}
