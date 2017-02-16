package com.moovel.mvp.demo.screens.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.moovel.mvp.demo.BaseActivity;
import com.moovel.mvp.demo.injection.AwesomeComponent;
import com.moovel.mvpbase.demo.R;

import javax.inject.Inject;

public class AwesomeActivity extends BaseActivity<AwesomeView, AwesomePresenter> implements AwesomeView {

    private static final String TAG = AwesomeActivity.class.getSimpleName();
    @Inject
    AwesomePresenter presenter;

    @Override
    public AwesomePresenter inject(AwesomeComponent dependencyGraph) {
        dependencyGraph.inject(this);
        return presenter;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.text)).setText(presenter.getInjectedString());
    }

    @Override
    public void log(String logthis) {
        Log.d(TAG, logthis);
    }
}
