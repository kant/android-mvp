package com.moovel.mvp.demo.injection;

import com.moovel.mvp.demo.screens.main.AwesomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AwesomeModule.class)
public interface AwesomeComponent {
    String getItem();


    void inject(AwesomeActivity mainActivity);
}
