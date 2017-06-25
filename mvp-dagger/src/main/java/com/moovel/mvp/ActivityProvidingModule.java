package com.moovel.mvp;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityProvidingModule {
    private final Activity activity;

    public ActivityProvidingModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }
}
