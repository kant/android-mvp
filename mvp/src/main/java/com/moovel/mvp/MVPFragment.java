/*
 * Copyright (c) 2017 moovel Group GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moovel.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Every Fragment should extend the MVPFragment, which provides some mvp base functionalities
 */
public abstract class MVPFragment<VIEW extends MVPView,
        PRESENTER extends MVPPresenter<VIEW>,
        DEPENDENCYGRAPH>
        extends Fragment {

    private final CompositeLifecycleInterceptor lifecycleInterceptor = new CompositeLifecycleInterceptor();

    private PRESENTER presenter;
    private int componentHash = 0;

    public MVPFragment() {
        if (!(this instanceof MVPView)) {
            throw new IllegalStateException(
                    String.format("The Fragment \"%s\" must implement a View!", this.getClass().getSimpleName()));
        }
    }

    public void addLifecycleInterceptor(LifecycleInterceptor interceptor) {
        lifecycleInterceptor.addLifecycleInterceptor(interceptor);
    }

    public void removeLifecycleInterceptor(LifecycleInterceptor interceptor) {
        lifecycleInterceptor.removeLifecycleInterceptor(interceptor);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DEPENDENCYGRAPH dependencyGraph = getDependencyGraph();
        componentHash = dependencyGraph.hashCode();
        presenter = inject(dependencyGraph);
    }

    private DEPENDENCYGRAPH getDependencyGraph() {
        try {
            return ((DependencyGraphProvider) getActivity().getApplication()).getComponent(getComponentClass());
        } catch (ClassCastException e) {
            throw new IllegalStateException(String.format("Your Application must implement %s",
                    DependencyGraphProvider.class.getSimpleName()));
        }
    }

    /**
     * @return the class object of the component, the fragment requires
     */
    protected abstract Class<DEPENDENCYGRAPH> getComponentClass();

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
        DEPENDENCYGRAPH dependencyGraph = getDependencyGraph();
        if (dependencyGraph.hashCode() != componentHash) {
            presenter = inject(dependencyGraph);
            componentHash = dependencyGraph.hashCode();
        }
        presenter.onResume();
        lifecycleInterceptor.doOnResume();
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

}
