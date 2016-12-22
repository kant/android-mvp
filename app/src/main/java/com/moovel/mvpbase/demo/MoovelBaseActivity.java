package com.moovel.mvpbase.demo;

import com.moovel.mvpbase.BasePresenter;
import com.moovel.mvpbase.MVPActivity;
import com.moovel.mvpbase.View;
import com.moovel.mvpbase.demo.injection.MoovelComponent;

public abstract class MoovelBaseActivity<V extends View, P extends BasePresenter<V>>
        extends MVPActivity<V, P, MoovelComponent, MoovelApplication> {

    @Override
    public MoovelApplication getDependencyGraphProvider() {
        return (MoovelApplication) getApplication();
    }
}
