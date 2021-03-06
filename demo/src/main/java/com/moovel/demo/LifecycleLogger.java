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

package com.moovel.demo;

import android.os.Bundle;

import com.moovel.mvp.lifecycle.LifecycleObserver;

import timber.log.Timber;

public final class LifecycleLogger implements LifecycleObserver {

    private final String className;

    public LifecycleLogger(String className) {
        this.className = className;
    }

    @Override
    public void onCreate(Bundle inState) {
        Timber.v("%s.onCreate(%s)", className, inState);
    }

    @Override
    public void onStart() {
        Timber.v("%s.onStart", className);
    }

    @Override
    public void onResume() {
        Timber.v("%s.onResume", className);
    }

    @Override
    public void onPause() {
        Timber.v("%s.onPause", className);
    }

    @Override
    public void onStop() {
        Timber.v("%s.onStop", className);
    }

    @Override
    public void onDestroy() {
        Timber.v("%s.onDestroy", className);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Timber.v("%s.onSaveInstanceState(%s)", className, outState);
    }

    @Override
    public void onLowMemory() {
        Timber.v("%s.onLowMemory()", className);
    }

    @Override
    public void onDestroyView() {
        Timber.v("%s.onDestroyView()", className);
    }
}
