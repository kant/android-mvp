package com.moovel.mvpbase.demo;

import com.moovel.mvpbase.MVPBase;
import com.moovel.mvpbase.demo.injection.MoovelComponent;
import com.moovel.mvpbase.rx.RxBasePresenter;
import com.moovel.mvpbase.rx.RxMVPActivity;

public abstract class MoovelBaseActivity<V extends MVPBase.View, P extends RxBasePresenter<V>>
        extends RxMVPActivity<V, P, MoovelComponent, MoovelApplication> {

    @Override
    public MoovelApplication getDependencyGraphProvider() {
        return (MoovelApplication) getApplication();
    }
}
