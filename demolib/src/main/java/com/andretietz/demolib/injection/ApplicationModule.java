package com.andretietz.demolib.injection;

import com.andretietz.demolib.injection.scopes.ApplicationScope;
import com.andretietz.demolib.model.ApplicationObject;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    @ApplicationScope
    ApplicationObject provideActivityObject() {
        return new ApplicationObject();
    }
}
