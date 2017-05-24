package com.moovel.mvp.demo.screens.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.moovel.mvp.demo.BaseActivity;
import com.moovel.mvpbase.demo.R;

import javax.inject.Inject;

public class AwesomeActivity extends BaseActivity<AwesomeView, AwesomePresenter> implements AwesomeView {

    private static final String TAG = AwesomeActivity.class.getSimpleName();
    @Inject
    AwesomePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected AwesomePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showText(String text) {
        ((TextView) findViewById(R.id.text)).setText(text);
    }
}
