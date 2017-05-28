package com.andretietz.demolib.injection;

import com.andretietz.demolib.injection.scopes.ActivityScope;
import com.andretietz.demolib.model.ActivityObject;

import dagger.Module;
import dagger.Provides;

@Module(includes = LibraryFragmentInjector.class)
public class ActivityModule {
    @Provides
    @ActivityScope
    ActivityObject provideActivityObject() {
        return new ActivityObject();
    }
}
