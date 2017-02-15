package com.moovel.mvp.demo;

import android.app.Application;

import com.moovel.mvp.demo.injection.DaggerMoovelComponent;
import com.moovel.mvp.demo.injection.MoovelComponent;
import com.moovel.mvp.demo.injection.MoovelComponentProvider;
import com.moovel.mvp.demo.injection.MoovelModule;

public class MoovelApplication extends Application implements MoovelComponentProvider {
    private MoovelComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMoovelComponent.builder().moovelModule(new MoovelModule()).build();
    }

    @Override
    public MoovelComponent getMoovelComponent() {
        return component;
    }
}
