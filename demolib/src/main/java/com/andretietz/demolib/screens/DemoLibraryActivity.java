package com.andretietz.demolib.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.andretietz.demolib.R;
import com.andretietz.demolib.model.ActivityObject;
import com.andretietz.demolib.model.ApplicationObject;
import com.moovel.mvp.DaggerMVPActivity;

public class DemoLibraryActivity extends DaggerMVPActivity<DemoLibraryActivityView, DemoLibraryActivityPresenter>
        implements DemoLibraryActivityView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer, new DemoLibraryFragment())
                .commit();


        findViewById(R.id.layerRecreate).setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new DemoLibraryFragment())
                    .commit();
        });
    }

    @Override
    public void showInjectedObjects(ApplicationObject apo, ActivityObject aco) {
        ((TextView) findViewById(R.id.text)).setText("DemoLibraryActivity: \n" + apo + "\n" + aco);
    }
}
