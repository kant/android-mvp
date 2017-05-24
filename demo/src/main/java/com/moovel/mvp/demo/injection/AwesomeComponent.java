package com.moovel.mvp.demo.injection;

import com.moovel.mvp.demo.AwesomeApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * root component
 **/
@Singleton
@Component(modules = {AndroidInjectionModule.class, InjectableModule.class, AwesomeModule.class})
public interface AwesomeComponent {
    void inject(AwesomeApplication application);
}
