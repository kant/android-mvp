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

import android.support.annotation.CallSuper;

import com.moovel.mvp.lifecycle.LifecycleObserver;

/**
 * This is the basic presenter, which should be extended by all Presenters.
 *
 * @param <V> View to handle within this presenter
 */
public abstract class MVPPresenter<V extends MVPView> {
    private final CompositeLifecycleObserver observer = new CompositeLifecycleObserver();
    private V view;

    public void attachView(V mvpView) {
        view = mvpView;
    }

    public void detachView() {
        view = null;
    }

    @Deprecated
    public boolean isViewAttached() {
        return view != null;
    }

    @Deprecated
    public V getViewOrThrow() throws ViewNotAttachedException {
        return getView();
    }

    public V getView() throws ViewNotAttachedException {
        if (view == null) throw new ViewNotAttachedException();
        return view;
    }

    public void addLifecycleObserver(LifecycleObserver interceptor) {
        observer.addLifecycleObserver(interceptor);
    }

    public void removeLifecycleObserver(LifecycleObserver interceptor) {
        observer.removeLifecycleObserver(interceptor);
    }

    /**
     * Called after the view was attached to this presenter
     */
    @CallSuper
    public void onCreate() {
        observer.doOnCreate();
    }

    /**
     * Called on the lifecycles {@code onStart()} of the Fragment/Activity
     */
    @CallSuper
    public void onStart() {
        observer.doOnStart();
    }

    /**
     * Called on the lifecycles {@code onResume()} of the Fragment/Activity
     */
    @CallSuper
    public void onResume() {
        observer.doOnResume();
    }

    /**
     * Called on the lifecycles {@code onPause()} of the Fragment/Activity
     */
    @CallSuper
    public void onPause() {
        observer.doOnPause();
    }

    /**
     * Called on the lifecycles {@code onStop()} of the Fragment/Activity
     */
    @CallSuper
    public void onStop() {
        observer.doOnStop();
    }

    /**
     * Called on the lifecycles {@code onDestroy()} of the Fragment/Activity before the
     * {@link MVPView} gets detached
     */
    @CallSuper
    public void onDestroy() {
        observer.doOnDestroy();
    }

}
