package com.andretietz.demolib.injection;

import android.app.Activity;

import com.andretietz.demolib.injection.scopes.ActivityScope;
import com.andretietz.demolib.model.ActivityObject;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module(includes = LibraryFragmentInjector.class)
public class ActivityModule {
    @Provides
    @ActivityScope
    ActivityObject provideActivityObject(Activity activity) {
        Timber.e("Activity: %s", activity);
        return new ActivityObject();
    }
}
