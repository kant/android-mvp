package com.moovel.mvp.demo;

import android.app.Application;

import com.moovel.mvp.DependencyGraphProvider;
import com.moovel.mvp.demo.injection.DaggerMoovelComponent;
import com.moovel.mvp.demo.injection.MoovelComponent;
import com.moovel.mvp.demo.injection.MoovelModule;

public class MoovelApplication extends Application implements DependencyGraphProvider<MoovelComponent> {
    private MoovelComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMoovelComponent.builder().moovelModule(new MoovelModule()).build();
    }

    @Override
    public MoovelComponent getDependencyGraph() {
        return component;
    }
}
