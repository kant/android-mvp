package com.moovel.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class MVPActivity<VIEW extends MVPBase.View,
        PRESENTER extends BasePresenter<VIEW>,
        DEPENDENCYGRAPH,
        PROVIDER extends DependencyGraphProvider<DEPENDENCYGRAPH>>
        extends AppCompatActivity implements Injectable<PRESENTER, PROVIDER, DEPENDENCYGRAPH> {

    private PRESENTER presenter;

    public MVPActivity() {
        if (!(this instanceof MVPBase.View)) {
            throw new IllegalStateException(
                    String.format("The Activity \"%s\" must implement a View!", this.getClass().getSimpleName()));
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = inject(getDependencyGraphProvider().getDependencyGraph());
        //noinspection unchecked
        presenter.attachView((VIEW) this);
        presenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter.detachView();
    }


}
