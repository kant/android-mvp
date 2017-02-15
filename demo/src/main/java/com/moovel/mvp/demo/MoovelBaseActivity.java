package com.moovel.mvp.demo;

import com.moovel.mvp.BasePresenter;
import com.moovel.mvp.MVPActivity;
import com.moovel.mvp.MVPView;
import com.moovel.mvp.demo.injection.MoovelComponent;
import com.moovel.mvp.demo.injection.MoovelComponentProvider;

public abstract class MoovelBaseActivity<V extends MVPView, P extends BasePresenter<V>>
        extends MVPActivity<V, P, MoovelComponent> {

    @Override
    protected MoovelComponent getDependencyGraph() {
        return ((MoovelComponentProvider)getApplication()).getMoovelComponent();
    }
}
