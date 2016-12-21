package com.moovel.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Every Fragment should extend the MVPFragment, which provides some mvp base functionalities
 */
public abstract class MVPFragment<VIEW extends MVPBase.View,
        PRESENTER extends BasePresenter<VIEW>,
        DEPENDENCYGRAPH,
        PROVIDER extends DependencyGraphProvider<DEPENDENCYGRAPH>>
        extends Fragment implements Injectable<PRESENTER, PROVIDER, DEPENDENCYGRAPH> {

    private PRESENTER presenter;

    public MVPFragment() {
        if (!(this instanceof MVPBase.View)) {
            throw new IllegalStateException(
                    String.format("The Fragment \"%s\" must implement a View!", this.getClass().getSimpleName()));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = inject(getDependencyGraphProvider().getDependencyGraph());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        //noinspection unchecked
        presenter.attachView((VIEW) this);
        presenter.onCreate();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter.detachView();
    }
}
