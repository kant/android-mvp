package com.moovel.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Every Fragment should extend the MVPFragment, which provides some mvp base functionalities
 */
public abstract class MVPFragment<VIEW extends View,
        PRESENTER extends BasePresenter<VIEW>,
        DEPENDENCYGRAPH,
        PROVIDER extends DependencyGraphProvider<DEPENDENCYGRAPH>>
        extends Fragment {

    private final CompositeLifecycleInterceptor lifecycleInterceptor = new CompositeLifecycleInterceptor();

    private PRESENTER presenter;

    public MVPFragment() {
        if (!(this instanceof View)) {
            throw new IllegalStateException(
                    String.format("The Fragment \"%s\" must implement a View!", this.getClass().getSimpleName()));
        }
    }

    public void addLifecycleInterceptor(LifecycleInterceptor interceptor) {
        lifecycleInterceptor.addLifecycleInterceptor(interceptor);
    }

    public void removeLifecycleInterceptor(LifecycleInterceptor interceptor) {
        lifecycleInterceptor.removeLifecyclePlugin(interceptor);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = inject(getDependencyGraphProvider().getDependencyGraph());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //noinspection unchecked
        presenter.attachView((VIEW) this);
        presenter.onCreate();
        lifecycleInterceptor.doOnCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
        lifecycleInterceptor.doOnStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        lifecycleInterceptor.doOnPause();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
        lifecycleInterceptor.doOnPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
        lifecycleInterceptor.doOnStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter.detachView();
        lifecycleInterceptor.doOnDestroy();
    }


    /**
     * use the component to inject your presenter and return it
     *
     * @param dependencyGraph for dependency injection
     * @return the presenter
     */
    protected abstract PRESENTER inject(DEPENDENCYGRAPH dependencyGraph);

    /**
     * @return a dependency graph provider
     */
    protected abstract PROVIDER getDependencyGraphProvider();
}
