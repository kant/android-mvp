package com.moovel.mvpbase.demo.injection;

import com.moovel.mvpbase.demo.screens.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MoovelModule.class)
public interface MoovelComponent {
    String getItem();


    void inject(MainActivity mainActivity);
}
