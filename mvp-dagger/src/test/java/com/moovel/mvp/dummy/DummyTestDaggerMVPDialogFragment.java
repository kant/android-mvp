package com.moovel.mvp.dummy;

import com.moovel.mvp.MVPDialogFragment;
import com.moovel.mvp.MVPPresenter;

public class DummyTestDaggerMVPDialogFragment extends MVPDialogFragment implements DummyTestMVPView {

    private DummyTestPresenter dummyTestPresenter = new DummyTestPresenter();
    @Override
    public MVPPresenter getPresenter() {
        return dummyTestPresenter;
    }

    @Override
    public void dummyInterfaceMethod() {

    }
}
