package com.moovel.mvp;

import android.app.Application;

import java.util.HashMap;

public abstract class MVPApplication extends Application implements ComponentProvider {

    private final HashMap<Class<?>, Object> componentMap = new HashMap<>();

    /**
     * Registers a DI Graph
     *
     * @param componentClass class to identify the component
     * @param component      to register
     */
    @Override
    public <T> void registerComponent(Class<T> componentClass, T component) {
        componentMap.put(componentClass, component);
    }

    /**
     * @param componentClass class of the component you want to receive
     * @param <T>            type of the component
     * @return the requested component
     * @throws IllegalStateException when there's no component registered
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getComponent(Class<T> componentClass) {
        Object component = componentMap.get(componentClass);
        if (component == null) {
            throw new IllegalStateException(
                    String.format("No component %s registered! Please register your component "
                                    + "using %s.registerComponent(component)",
                            componentClass.getSimpleName(), ComponentProvider.class.getSimpleName()
                    )
            );
        }
        return (T) component;
    }
}
