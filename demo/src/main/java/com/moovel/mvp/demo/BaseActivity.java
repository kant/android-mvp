package com.moovel.mvp.demo;

import com.moovel.mvp.MVPActivity;
import com.moovel.mvp.MVPPresenter;
import com.moovel.mvp.MVPView;
import com.moovel.mvp.demo.injection.AwesomeComponent;

public abstract class BaseActivity<V extends MVPView, P extends MVPPresenter<V>>
        extends MVPActivity<V, P, AwesomeComponent> {

    @Override
    protected Class<AwesomeComponent> getComponentClass() {
        return AwesomeComponent.class;
    }

}
