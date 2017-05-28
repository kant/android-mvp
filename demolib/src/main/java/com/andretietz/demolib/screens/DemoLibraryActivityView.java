package com.andretietz.demolib.screens;

import com.andretietz.demolib.model.ActivityObject;
import com.andretietz.demolib.model.ApplicationObject;
import com.moovel.mvp.MVPView;

public interface DemoLibraryActivityView extends MVPView {
    void showInjectedObjects(ApplicationObject apo, ActivityObject aco);
}
