package com.moovel.mvp.lifecycle;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static com.moovel.mvp.lifecycle.LifecycleEvent.CREATE;
import static com.moovel.mvp.lifecycle.LifecycleEvent.DESTROY;
import static com.moovel.mvp.lifecycle.LifecycleEvent.PAUSE;
import static com.moovel.mvp.lifecycle.LifecycleEvent.RESUME;
import static com.moovel.mvp.lifecycle.LifecycleEvent.START;
import static com.moovel.mvp.lifecycle.LifecycleEvent.STOP;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({CREATE, START, RESUME, PAUSE, STOP, DESTROY})
public @interface LifecycleEvent {
    int CREATE = 0;
    int START = 1;
    int RESUME = 2;
    int PAUSE = 3;
    int STOP = 4;
    int DESTROY = 5;
}
