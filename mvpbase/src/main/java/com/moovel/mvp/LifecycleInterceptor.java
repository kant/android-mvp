package com.moovel.mvp;

public interface LifecycleInterceptor {
    void doOnCreate();
    void doOnStart();
    void doOnResume();
    void doOnPause();
    void doOnStop();
    void doOnDestroy();
}
