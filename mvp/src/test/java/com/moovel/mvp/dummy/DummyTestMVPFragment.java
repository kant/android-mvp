package com.moovel.mvp.dummy;

import com.moovel.mvp.MVPFragment;
import com.moovel.mvp.MVPPresenter;

public class DummyTestMVPFragment extends MVPFragment implements DummyTestMVPView {

    private DummyTestPresenter dummyTestPresenter = new DummyTestPresenter();
    @Override
    public MVPPresenter getPresenter() {
        return dummyTestPresenter;
    }

    @Override
    public void dummyInterfaceMethod() {

    }
}
