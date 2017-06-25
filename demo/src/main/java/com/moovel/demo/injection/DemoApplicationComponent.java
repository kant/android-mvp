package com.moovel.demo.injection;

import android.app.Application;

import com.andretietz.demolib.injection.ApplicationModule;
import com.andretietz.demolib.injection.DemoLibraryInjections;
import com.andretietz.demolib.injection.scopes.ApplicationScope;
import com.moovel.demo.DemoApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * root component
 **/
@ApplicationScope
@Component(modules = {
        AndroidInjectionModule.class, // dagger module for android support
        ApplicationModule.class, // providing the ApplicationObject
        DemoAppInjections.class, // module to inject app lvl activities
        DemoLibraryInjections.class // module to inject lib lvl activities
})
public interface DemoApplicationComponent {
    void inject(DemoApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        DemoApplicationComponent build();
    }
}
