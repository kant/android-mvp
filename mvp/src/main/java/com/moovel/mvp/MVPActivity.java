package com.moovel.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class MVPActivity<VIEW extends MVPView,
        PRESENTER extends MVPPresenter<VIEW>,
        DEPENDENCYGRAPH>
        extends AppCompatActivity {

    private final CompositeLifecycleInterceptor lifecycleInterceptor = new CompositeLifecycleInterceptor();
    private PRESENTER presenter;
    private int componentHash = 0;

    public MVPActivity() {
        if (!(this instanceof MVPView)) {
            throw new IllegalStateException(
                    String.format("The Activity \"%s\" must implement a View!", this.getClass().getSimpleName()));
        }
    }

    public void addLifecycleInterceptor(LifecycleInterceptor interceptor) {
        lifecycleInterceptor.addLifecycleInterceptor(interceptor);
    }

    public void removeLifecycleInterceptor(LifecycleInterceptor interceptor) {
        lifecycleInterceptor.removeLifecycleInterceptor(interceptor);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DEPENDENCYGRAPH dependencyGraph = getDependencyGraph();
        componentHash = dependencyGraph.hashCode();
        presenter = inject(dependencyGraph);
        //noinspection unchecked
        presenter.attachView((VIEW) this);
        presenter.onCreate();
        lifecycleInterceptor.doOnCreate();
    }

    /**
     * @return the class object of the component, the activity requires
     */
    protected abstract Class<DEPENDENCYGRAPH> getComponentClass();

    private DEPENDENCYGRAPH getDependencyGraph() {
        try {
            return ((DependencyGraphProvider) getApplication()).getComponent(getComponentClass());
        } catch (ClassCastException e) {
            throw new IllegalStateException(String.format("Your Application must implement %s",
                    DependencyGraphProvider.class.getSimpleName()));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
        lifecycleInterceptor.doOnStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DEPENDENCYGRAPH dependencyGraph = getDependencyGraph();
        if (dependencyGraph.hashCode() != componentHash) {
            presenter = inject(dependencyGraph);
            componentHash = dependencyGraph.hashCode();
        }
        presenter.onResume();
        lifecycleInterceptor.doOnResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
        lifecycleInterceptor.doOnPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
        lifecycleInterceptor.doOnStop();
    }

    @Override
    protected void onDestroy() {
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

}
