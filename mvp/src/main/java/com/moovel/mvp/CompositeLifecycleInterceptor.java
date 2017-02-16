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

import java.util.HashSet;
import java.util.Set;

final class CompositeLifecycleInterceptor implements LifecycleInterceptor {

    private final Set<LifecycleInterceptor> plugins = new HashSet<>();

    public void addLifecycleInterceptor(LifecycleInterceptor plugin) {
        plugins.add(plugin);
    }

    public void removeLifecycleInterceptor(LifecycleInterceptor plugin) {
        plugins.remove(plugin);
    }

    @Override
    public void doOnCreate() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnCreate();
        }
    }

    @Override
    public void doOnStart() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnStart();
        }
    }

    @Override
    public void doOnResume() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnResume();
        }
    }

    @Override
    public void doOnPause() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnPause();
        }
    }

    @Override
    public void doOnStop() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnStop();
        }
    }

    @Override
    public void doOnDestroy() {
        for (LifecycleInterceptor interceptor : plugins) {
            interceptor.doOnDestroy();
        }
    }
}
