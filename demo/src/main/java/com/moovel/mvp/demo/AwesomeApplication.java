package com.moovel.mvp.demo;

import com.moovel.mvp.MVPApplication;
import com.moovel.mvp.demo.injection.AwesomeComponent;
import com.moovel.mvp.demo.injection.AwesomeModule;
import com.moovel.mvp.demo.injection.DaggerAwesomeComponent;

public class AwesomeApplication extends MVPApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        registerComponent(AwesomeComponent.class, DaggerAwesomeComponent
                .builder()
                .awesomeModule(new AwesomeModule())
                .build());
    }

}
