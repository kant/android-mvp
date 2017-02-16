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

package com.moovel.mvp.lifecycle;


import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.moovel.mvp.LifecycleInterceptor;

import java.util.HashSet;
import java.util.Set;

import static com.moovel.mvp.lifecycle.LifecycleEvent.CREATE;
import static com.moovel.mvp.lifecycle.LifecycleEvent.DESTROY;
import static com.moovel.mvp.lifecycle.LifecycleEvent.PAUSE;
import static com.moovel.mvp.lifecycle.LifecycleEvent.RESUME;
import static com.moovel.mvp.lifecycle.LifecycleEvent.START;
import static com.moovel.mvp.lifecycle.LifecycleEvent.STOP;

public final class LifecycleEventScheduler<T> implements LifecycleInterceptor {

    private final LifecycleEventListener<T> eventListener;

    private final SparseArray<Set<T>> container;

    /**
     * @param eventListener will be called once foreach enqueued item event
     */
    public LifecycleEventScheduler(@NonNull LifecycleEventListener<T> eventListener) {
        this.eventListener = eventListener;
        this.container = new SparseArray<>();
    }

    /**
     * Enqueues an item event
     *
     * @param event when to trigger
     * @param item  that needs to to something
     */
    public void enqueue(@LifecycleEvent int event, T item) {
        Set<T> ts = container.get(event);
        if (ts == null) ts = new HashSet<>();
        ts.add(item);
    }

    @Override
    public void doOnCreate() {
        execute(CREATE);
    }

    @Override
    public void doOnStart() {
        execute(START);
    }

    @Override
    public void doOnResume() {
        execute(RESUME);
    }

    @Override
    public void doOnPause() {
        execute(PAUSE);
    }

    @Override
    public void doOnStop() {
        execute(STOP);
    }

    @Override
    public void doOnDestroy() {
        execute(DESTROY);
    }

    private void execute(@LifecycleEvent int event) {
        Set<T> itemset = container.get(event);
        if (itemset == null) return;
        for (T item : itemset) {
            eventListener.onEvent(event, item);
        }
        itemset.clear();
    }

    public interface LifecycleEventListener<T> {
        void onEvent(@LifecycleEvent int event, T item);
    }
}
