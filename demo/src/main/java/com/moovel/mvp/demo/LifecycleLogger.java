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

package com.moovel.mvp.demo;

import com.moovel.mvp.lifecycle.LifecycleInterceptor;

import timber.log.Timber;

public final class LifecycleLogger implements LifecycleInterceptor {

    private final String className;

    public LifecycleLogger(String className) {
        this.className = className;
    }

    @Override
    public void doOnCreate() {
        Timber.v("%s.onCreate", className);
    }

    @Override
    public void doOnStart() {
        Timber.v("%s.onStart", className);
    }

    @Override
    public void doOnResume() {
        Timber.v("%s.onResume", className);
    }

    @Override
    public void doOnPause() {
        Timber.v("%s.onPause", className);
    }

    @Override
    public void doOnStop() {
        Timber.v("%s.onStop", className);
    }

    @Override
    public void doOnDestroy() {
        Timber.v("%s.onDestroy", className);
    }
}
