package com.moovel.mvp.dummy;

import android.app.Application;

import com.moovel.mvp.R;

public class DummyTestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setTheme(R.style.Theme_AppCompat);
    }
}
