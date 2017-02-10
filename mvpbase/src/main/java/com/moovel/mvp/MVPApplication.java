package com.moovel.mvp;

import android.app.Application;

import java.util.HashMap;

public abstract class MVPApplication extends Application {

    private final HashMap<Class<?>, Object> componentMap = new HashMap<>();

    /**
     * Registers a DI Graph
     *
     * @param component to register
     */
    public void registerComponent(Object component) {
        componentMap.put(component.getClass(), component);
    }

    /**
     * @param componentClass class of the component you want to receive
     * @param <T>            type of the component
     * @return the requested component
     * @throws IllegalStateException when there's no component registered
     */
    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<T> componentClass) {
        Object component = componentMap.get(componentClass);
        if (component == null) {
            throw new IllegalStateException(
                    String.format("No component %s registered! Please register your component "
                                    + "using %s.registerComponent(component)",
                            componentClass.getSimpleName(), MVPApplication.class.getSimpleName()
                    )
            );
        }
        return (T) component;
    }
}
