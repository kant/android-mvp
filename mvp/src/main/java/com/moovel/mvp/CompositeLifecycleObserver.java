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

import com.moovel.mvp.lifecycle.LifecycleObserver;

import java.util.LinkedList;
import java.util.List;

final class CompositeLifecycleObserver implements LifecycleObserver {

    final List<LifecycleObserver> observers = new LinkedList<>();

    public void addLifecycleObserver(LifecycleObserver plugin) {
        observers.add(plugin);
    }

    public void removeLifecycleObserver(LifecycleObserver plugin) {
        observers.remove(plugin);
    }


    @Override
    public void onCreate(Bundle inState) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onCreate(inState);
        }
    }

    @Override
    public void onStart() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onStart();
        }
    }

    @Override
    public void onResume() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onResume();
        }
    }

    @Override
    public void onPause() {
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).onPause();
        }
    }

    @Override
    public void onStop() {
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).onSaveInstanceState(outState);
        }
    }

    @Override
    public void onLowMemory() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).onLowMemory();
        }
    }
}
