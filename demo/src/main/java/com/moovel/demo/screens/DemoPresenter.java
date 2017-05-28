package com.moovel.demo.screens;

import com.andretietz.demolib.injection.scopes.ActivityScope;
import com.andretietz.demolib.model.ActivityObject;
import com.andretietz.demolib.model.ApplicationObject;
import com.moovel.mvp.MVPPresenter;
import com.moovel.mvp.ViewNotAttachedException;

import javax.inject.Inject;

@ActivityScope
public class DemoPresenter extends MVPPresenter<DemoView> {
    private final ApplicationObject apo;
    private final ActivityObject aco;

    @Inject
    public DemoPresenter(ApplicationObject apo, ActivityObject aco) {
        this.apo = apo;
        this.aco = aco;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            getViewOrThrow().showInjectedObjects(apo, aco);
        } catch (ViewNotAttachedException e) {
            // view not available
        }
    }

    public void onButtonClicked() {
        try {
            getViewOrThrow().openLibraryActivity();
        } catch (ViewNotAttachedException e) {
            // view not available
        }
    }
}
