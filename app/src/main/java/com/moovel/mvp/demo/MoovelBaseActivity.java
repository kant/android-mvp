package com.moovel.mvp.demo;

import com.moovel.mvp.BasePresenter;
import com.moovel.mvp.MVPActivity;
import com.moovel.mvp.View;
import com.moovel.mvp.demo.injection.MoovelComponent;

public abstract class MoovelBaseActivity<V extends View, P extends BasePresenter<V>>
        extends MVPActivity<V, P, MoovelComponent, MoovelApplication> {

    @Override
    public MoovelApplication getDependencyGraphProvider() {
        return (MoovelApplication) getApplication();
    }
}
