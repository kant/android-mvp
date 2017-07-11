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


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import java.util.HashSet;
import java.util.Set;

import static com.moovel.mvp.lifecycle.LifecycleEvent.CREATE;
import static com.moovel.mvp.lifecycle.LifecycleEvent.DESTROY;
import static com.moovel.mvp.lifecycle.LifecycleEvent.LOWMEMORY;
import static com.moovel.mvp.lifecycle.LifecycleEvent.PAUSE;
import static com.moovel.mvp.lifecycle.LifecycleEvent.RESUME;
import static com.moovel.mvp.lifecycle.LifecycleEvent.SAVEINSTANCESTATE;
import static com.moovel.mvp.lifecycle.LifecycleEvent.START;
import static com.moovel.mvp.lifecycle.LifecycleEvent.STOP;

public final class LifecycleEventScheduler<T> implements LifecycleObserver {

    final LifecycleEventListener<T> eventListener;

    final SparseArray<Set<T>> container;

    /**
     * @param eventListener will be called once foreach enqueued item event
     */
    public LifecycleEventScheduler(@NonNull LifecycleEventListener<T> eventListener) {
        this.eventListener = eventListener;
        this.container = new SparseArray<>();
    }

    /**
     * Enqueues an item event. This will be executed ONCE
     *
     * @param event when to trigger
     * @param item  that needs to to something
     */
    public void enqueue(@LifecycleEvent int event, T item) {
        Set<T> ts = container.get(event);
        if (ts == null) ts = new HashSet<>();
        ts.add(item);
        container.append(event, ts);
    }

    @Override
    public void onCreate(Bundle inState) {
        execute(CREATE, inState);
    }

    @Override
    public void onStart() {
        execute(START, null);
    }

    @Override
    public void onResume() {
        execute(RESUME, null);
    }

    @Override
    public void onPause() {
        execute(PAUSE, null);
    }

    @Override
    public void onStop() {
        execute(STOP, null);
    }

    @Override
    public void onDestroy() {
        execute(DESTROY, null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        execute(SAVEINSTANCESTATE, outState);
    }

    @Override
    public void onLowMemory() {
        execute(LOWMEMORY, null);
    }

    private void execute(@LifecycleEvent int event, @Nullable Bundle state) {
        Set<T> itemset = container.get(event);
        if (itemset == null) return;
        for (T item : itemset) {
            eventListener.onEvent(event, item, state);
        }
        itemset.clear();
    }

    public interface LifecycleEventListener<T> {
        /**
         * Called on every lifecycle event
         *
         * @param event {@link LifecycleEvent} which was triggered
         * @param item  you want to do smth with
         * @param state only emitted in {@link #onCreate(Bundle)} and {@link #onSaveInstanceState(Bundle)}
         */
        void onEvent(@LifecycleEvent int event, T item, @Nullable Bundle state);
    }
}
