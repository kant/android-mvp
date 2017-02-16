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

import android.app.Application;

import java.util.HashMap;

public abstract class MVPApplication extends Application implements DependencyGraphProvider {

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
                            componentClass.getSimpleName(), DependencyGraphProvider.class.getSimpleName()
                    )
            );
        }
        return (T) component;
    }
}
