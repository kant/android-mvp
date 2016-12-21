package com.moovel.mvpbase;

interface Injectable<PRESENTER, PROVIDER, GRAPH> {
    /**
     * use the component to inject your presenter and return it
     *
     * @param dependencyGraph for dependency injection
     * @return the presenter
     */
    PRESENTER inject(GRAPH dependencyGraph);

    /**
     * @return a dependency graph provider
     */
    PROVIDER getDependencyGraphProvider();
}
