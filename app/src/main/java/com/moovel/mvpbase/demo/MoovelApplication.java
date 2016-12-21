package com.moovel.mvpbase.demo;

import android.app.Application;

import com.moovel.mvpbase.DependencyGraphProvider;
import com.moovel.mvpbase.demo.injection.DaggerMoovelComponent;
import com.moovel.mvpbase.demo.injection.MoovelComponent;
import com.moovel.mvpbase.demo.injection.MoovelModule;

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
