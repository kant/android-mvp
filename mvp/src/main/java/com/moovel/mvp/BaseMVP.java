package com.moovel.mvp;

interface BaseMVP<VIEW extends MVPView, PRESENTER extends MVPPresenter<VIEW>> {
    PRESENTER getPresenter();
}
