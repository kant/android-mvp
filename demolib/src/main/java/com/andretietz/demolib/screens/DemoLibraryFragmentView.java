package com.andretietz.demolib.screens;

import com.andretietz.demolib.model.ActivityObject;
import com.andretietz.demolib.model.ApplicationObject;
import com.andretietz.demolib.model.FragmentObject;
import com.moovel.mvp.MVPView;

public interface DemoLibraryFragmentView extends MVPView {
    void showInjectedObjects(ApplicationObject apo, ActivityObject aco, FragmentObject fro);
}
