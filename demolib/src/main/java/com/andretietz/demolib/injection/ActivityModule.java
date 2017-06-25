package com.andretietz.demolib.injection;

import android.app.Activity;

import com.andretietz.demolib.injection.scopes.ActivityScope;
import com.andretietz.demolib.model.ActivityObject;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module(includes = LibraryFragmentInjector.class)
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    ActivityObject provideActivityObject(Activity activity) {
        Timber.e("Activity: %s", activity);
        return new ActivityObject();
    }
}
