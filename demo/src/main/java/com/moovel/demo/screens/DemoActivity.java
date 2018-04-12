package com.moovel.demo.screens;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.andretietz.demolib.model.ActivityObject;
import com.andretietz.demolib.model.ApplicationObject;
import com.andretietz.demolib.screens.DemoLibraryActivity;
import com.moovel.mvp.DaggerMVPActivity;
import com.moovel.demo.R;

public class DemoActivity extends DaggerMVPActivity<DemoView, DemoPresenter> implements DemoView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(v -> getPresenter().onButtonClicked());
    }

    @Override @SuppressLint("SetTextI18n")
    public void showInjectedObjects(ApplicationObject apo, ActivityObject aco) {
        ((TextView) findViewById(R.id.textTitle)).setText(
                "DemoActivity: \n" +
                        apo + "\n" + aco);
    }

    @Override
    public void openLibraryActivity() {
        startActivity(new Intent(this, DemoLibraryActivity.class));
    }
}
