package com.moovel.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class MVPActivity<VIEW extends View,
        PRESENTER extends BasePresenter<VIEW>,
        DEPENDENCYGRAPH,
        PROVIDER extends DependencyGraphProvider<DEPENDENCYGRAPH>>
        extends AppCompatActivity {

    private final CompositeLifecycleInterceptor lifecycleInterceptor = new CompositeLifecycleInterceptor();
    private PRESENTER presenter;

    public MVPActivity() {
        if (!(this instanceof View)) {
            throw new IllegalStateException(
                    String.format("The Activity \"%s\" must implement a View!", this.getClass().getSimpleName()));
        }
    }

    public void addLifecycleInterceptor(LifecycleInterceptor interceptor) {
        lifecycleInterceptor.addLifecycleInterceptor(interceptor);
    }

    public void removeLifecycleInterceptor(LifecycleInterceptor interceptor) {
        lifecycleInterceptor.removeLifecyclePlugin(interceptor);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = inject(getDependencyGraphProvider().getDependencyGraph());
        //noinspection unchecked
        presenter.attachView((VIEW) this);
        presenter.onCreate();
        lifecycleInterceptor.doOnCreate();
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

    /**
     * @return a dependency graph provider
     */
    protected abstract PROVIDER getDependencyGraphProvider();

}
