package com.moovel.mvp;

public abstract class SimpleActivity extends MVPActivity<View,
        SimpleActivity.SimplePresenter, Object, SimpleActivity.SimpleDependencyGraphProvider> implements View {

    private final SimplePresenter presenter = new SimplePresenter();

    @Override
    protected SimplePresenter inject(Object dependencyGraph) {
        return presenter;
    }

    static class SimplePresenter extends BasePresenter<View> {
        SimplePresenter() {
        }
    }

    static class SimpleDependencyGraphProvider implements DependencyGraphProvider<Object> {
        @Override
        public Object getDependencyGraph() {
            return null;
        }
    }
}
