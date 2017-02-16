package com.moovel.mvp;


import android.support.annotation.CallSuper;

/**
 * This is the basic presenter, which should be extended by all Presenters.
 *
 * @param <V> View to handle within this presenter
 */
public abstract class MVPPresenter<V extends MVPView> {
    private final CompositeLifecycleInterceptor lifecycleInterceptor = new CompositeLifecycleInterceptor();
    private V view;

    public void attachView(V mvpView) {
        view = mvpView;
    }

    public void detachView() {
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view;
    }

    public void addLifecycleInterceptor(LifecycleInterceptor interceptor) {
        lifecycleInterceptor.addLifecycleInterceptor(interceptor);
    }

    public void removeLifecycleInterceptor(LifecycleInterceptor interceptor) {
        lifecycleInterceptor.removeLifecycleInterceptor(interceptor);
    }

    /**
     * Called after the view was attached to this presenter
     */
    @CallSuper
    public void onCreate() {
        lifecycleInterceptor.doOnCreate();
    }

    /**
     * Called on the lifecycles {@code onStart()} of the Fragment/Activity
     */
    @CallSuper
    public void onStart() {
        lifecycleInterceptor.doOnStart();
    }

    /**
     * Called on the lifecycles {@code onResume()} of the Fragment/Activity
     */
    @CallSuper
    public void onResume() {
        lifecycleInterceptor.doOnResume();
    }

    /**
     * Called on the lifecycles {@code onPause()} of the Fragment/Activity
     */
    @CallSuper
    public void onPause() {
        lifecycleInterceptor.doOnPause();
    }

    /**
     * Called on the lifecycles {@code onStop()} of the Fragment/Activity
     */
    @CallSuper
    public void onStop() {
        lifecycleInterceptor.doOnStop();
    }

    /**
     * Called on the lifecycles {@code onDestroy()} of the Fragment/Activity before the
     * {@link MVPView} gets detached
     */
    @CallSuper
    public void onDestroy() {
        lifecycleInterceptor.doOnDestroy();
    }

}
