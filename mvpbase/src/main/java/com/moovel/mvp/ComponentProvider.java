package com.moovel.mvp;


public interface ComponentProvider {

    /**
     * registers a component for injection
     *
     * @param componentClass component class interface that is registered
     * @param component      to register
     * @param <T>
     */
    <T> void registerComponent(Class<T> componentClass, T component);

    /**
     * @param componentClass to receive
     * @param <T>
     * @return the component if registered
     * @throws IllegalStateException when the component is not registered
     */
    <T> T getComponent(Class<T> componentClass);
}
