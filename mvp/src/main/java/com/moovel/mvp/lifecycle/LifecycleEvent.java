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

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static com.moovel.mvp.lifecycle.LifecycleEvent.CREATE;
import static com.moovel.mvp.lifecycle.LifecycleEvent.DESTROY;
import static com.moovel.mvp.lifecycle.LifecycleEvent.DESTROYVIEW;
import static com.moovel.mvp.lifecycle.LifecycleEvent.LOWMEMORY;
import static com.moovel.mvp.lifecycle.LifecycleEvent.PAUSE;
import static com.moovel.mvp.lifecycle.LifecycleEvent.RESUME;
import static com.moovel.mvp.lifecycle.LifecycleEvent.SAVEINSTANCESTATE;
import static com.moovel.mvp.lifecycle.LifecycleEvent.START;
import static com.moovel.mvp.lifecycle.LifecycleEvent.STOP;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({CREATE, START, RESUME, PAUSE, STOP, DESTROY, SAVEINSTANCESTATE, LOWMEMORY, DESTROYVIEW})
public @interface LifecycleEvent {
    int CREATE = 0;
    int START = 1;
    int RESUME = 2;
    int PAUSE = 3;
    int STOP = 4;
    int DESTROY = 5;
    int SAVEINSTANCESTATE = 6;
    int LOWMEMORY = 7;
    int DESTROYVIEW = 8;
}
