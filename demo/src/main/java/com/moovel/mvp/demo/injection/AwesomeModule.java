package com.moovel.mvp.demo.injection;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * module providing something
 */
@Module
public class AwesomeModule {
    @Provides
    @Singleton
    public String provideMe() {
        return "Counter every 5 Seconds: ";
    }
}
