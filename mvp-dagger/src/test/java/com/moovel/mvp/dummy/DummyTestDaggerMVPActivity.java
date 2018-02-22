package com.moovel.mvp.dummy;

import com.moovel.mvp.MVPActivity;
import com.moovel.mvp.MVPPresenter;

public class DummyTestDaggerMVPActivity extends MVPActivity implements DummyTestMVPView {

    private DummyTestPresenter dummyTestPresenter = new DummyTestPresenter();

    @Override
    public MVPPresenter getPresenter() {
        return dummyTestPresenter;
    }

    @Override
    public void dummyInterfaceMethod() {

    }
}
