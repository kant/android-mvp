package com.moovel.mvpbase;

import android.support.annotation.CallSuper;

/**
 * This is the basic presenter, which should be extended by all Presenters.
 *
 * @param <V> View to handle within this presenter
 */
public abstract class BasePresenter<V extends MVPBase.View> extends MVPBase.Presenter<V> {
    private V view;

    @Override
    void attachView(V mvpView) {
        view = mvpView;
    }

    @Override
    void detachView() {
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view;
    }

    /**
     * Called after the view was attached to this presenter
     */
    @CallSuper
    public void onCreate() {
    }

    /**
     * Called on the lifecycles {@code onStart()} of the Fragment/Activity
     */
    @CallSuper
    public void onStart() {
    }

    /**
     * Called on the lifecycles {@code onStop()} of the Fragment/Activity
     */
    @CallSuper
    public void onStop() {
    }

    /**
     * Called on the lifecycles {@code onDestroy()} of the Fragment/Activity before the
     * {@link com.moovel.mvpbase.MVPBase.View} gets detached
     */
    @CallSuper
    public void onDestroy() {
    }

}
