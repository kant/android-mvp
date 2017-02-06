package com.moovel.mvp;

public abstract class SimpleActivity extends MVPActivity<MVPView,
        SimpleActivity.SimplePresenter, Object> implements MVPView {

    private final SimplePresenter presenter = new SimplePresenter();

    @Override
    protected SimplePresenter inject(Object dependencyGraph) {
        return presenter;
    }

    static class SimplePresenter extends BasePresenter<MVPView> {
        SimplePresenter() {
        }
    }

    @Override
    protected Object getDependencyGraph() {
        return null;
    }
}
