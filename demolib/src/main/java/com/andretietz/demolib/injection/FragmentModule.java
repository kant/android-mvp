package com.andretietz.demolib.injection;

import com.andretietz.demolib.injection.scopes.FragmentScope;
import com.andretietz.demolib.model.FragmentObject;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    @Provides
    @FragmentScope
    FragmentObject provideFragmentObject() {
        return new FragmentObject();
    }
}
