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

import com.moovel.mvp.lifecycle.LifecycleInterceptor;

import java.util.LinkedList;
import java.util.List;

final class CompositeLifecycleInterceptor implements LifecycleInterceptor {

    final List<LifecycleInterceptor> interceptors = new LinkedList<>();

    public void addLifecycleInterceptor(LifecycleInterceptor plugin) {
        interceptors.add(plugin);
    }

    public void removeLifecycleInterceptor(LifecycleInterceptor plugin) {
        interceptors.remove(plugin);
    }


    @Override
    public void doOnCreate() {
        for (int i = 0; i < interceptors.size(); i++) {
            interceptors.get(i).doOnCreate();
        }
    }

    @Override
    public void doOnStart() {
        for (int i = 0; i < interceptors.size(); i++) {
            interceptors.get(i).doOnStart();
        }
    }

    @Override
    public void doOnResume() {
        for (int i = 0; i < interceptors.size(); i++) {
            interceptors.get(i).doOnResume();
        }
    }

    @Override
    public void doOnPause() {
        for (int i = interceptors.size() - 1; i >= 0; i--) {
            interceptors.get(i).doOnPause();
        }
    }

    @Override
    public void doOnStop() {
        for (int i = interceptors.size() - 1; i >= 0; i--) {
            interceptors.get(i).doOnStop();
        }
    }

    @Override
    public void doOnDestroy() {
        for (int i = interceptors.size() - 1; i >= 0; i--) {
            interceptors.get(i).doOnDestroy();
        }
    }
}
