package com.andretietz.demolib.injection;

import android.app.Application;

import com.andretietz.demolib.injection.scopes.ApplicationScope;
import com.andretietz.demolib.model.ApplicationObject;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module
public class ApplicationModule {
    @Provides
    @ApplicationScope
    ApplicationObject provideActivityObject(Application application) {
        Timber.e("Application: %s", application);
        return new ApplicationObject();
    }
}
