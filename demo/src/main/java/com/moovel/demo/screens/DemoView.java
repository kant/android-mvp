package com.moovel.demo.screens;

import com.andretietz.demolib.model.ActivityObject;
import com.andretietz.demolib.model.ApplicationObject;
import com.moovel.mvp.MVPView;

public interface DemoView extends MVPView {
    void showInjectedObjects(ApplicationObject apo, ActivityObject aco);
    void openLibraryActivity();
}
