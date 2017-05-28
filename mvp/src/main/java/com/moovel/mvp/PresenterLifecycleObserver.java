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

import com.moovel.mvp.lifecycle.LifecycleObserver;

class PresenterLifecycleObserver<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>>
        implements LifecycleObserver {

    private final PRESENTER presenter;
    private final VIEW view;

    PresenterLifecycleObserver(VIEW view, PRESENTER presenter) {
        this.presenter = presenter;
        this.view = view;
    }

    @Override
    public void doOnCreate() {
        presenter.onCreate();
    }

    @Override
    public void doOnStart() {
        presenter.attachView(view);
        presenter.onStart();
    }

    @Override
    public void doOnResume() {
        presenter.onResume();
    }

    @Override
    public void doOnPause() {
        presenter.onPause();
    }

    @Override
    public void doOnStop() {
        presenter.onStop();
        presenter.detachView();
    }

    @Override
    public void doOnDestroy() {
        presenter.onDestroy();
    }
}
