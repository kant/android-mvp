package com.moovel.mvpbase.demo.screens.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.moovel.mvpbase.demo.MoovelBaseActivity;
import com.moovel.mvpbase.demo.R;
import com.moovel.mvpbase.demo.injection.MoovelComponent;

import javax.inject.Inject;

public class MainActivity extends MoovelBaseActivity<MainView, MainPresenter> implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject
    MainPresenter presenter;

    @Override
    public MainPresenter inject(MoovelComponent dependencyGraph) {
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