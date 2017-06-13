package com.andretietz.demolib.screens;

import com.andretietz.demolib.injection.scopes.FragmentScope;
import com.andretietz.demolib.model.ActivityObject;
import com.andretietz.demolib.model.ApplicationObject;
import com.andretietz.demolib.model.FragmentObject;
import com.moovel.mvp.MVPPresenter;
import com.moovel.mvp.ViewNotAttachedException;

import javax.inject.Inject;

@FragmentScope
public class DemoLibraryFragmentPresenter extends MVPPresenter<DemoLibraryFragmentView> {
    private final ApplicationObject apo;
    private final ActivityObject aco;
    private final FragmentObject fro;

    @Inject
    public DemoLibraryFragmentPresenter(ApplicationObject apo,
                                        ActivityObject aco,
                                        FragmentObject fro) {
        this.apo = apo;
        this.aco = aco;
        this.fro = fro;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            getView().showInjectedObjects(apo, aco, fro);
        } catch (ViewNotAttachedException e) {
            // view not attached, so don't do anything
        }
    }
}
