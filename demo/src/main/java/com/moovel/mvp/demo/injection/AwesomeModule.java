package com.moovel.mvp.demo.injection;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AwesomeModule {

    @Provides
    @Singleton
    public String provideMe() {
        return "Some injected item";
    }
}
