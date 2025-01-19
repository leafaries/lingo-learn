package com.lingolearn;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class DIContainer {
    private final Map<Class<?>, Object> container = new HashMap<>();

    // Register a dependency in the container
    public void register(Class<?> clazz, Object instance) {
        container.put(clazz, instance);
    }

    // Resolve and get an instance of a dependency
    public <T> T resolve(Class<T> clazz) {
        return clazz.cast(container.get(clazz));
    }

    // Resolve and create an instance if it's not already in the container
    public <T> T resolveOrCreate(Class<T> clazz) throws Exception {
        T instance = resolve(clazz);
        if (instance == null) {
            // Resolve constructor dependencies using reflection
            Constructor<?>[] constructors = clazz.getConstructors();
            if (constructors.length > 0) {
                Constructor<?> constructor = constructors[0];
                Parameter[] params = constructor.getParameters();
                Object[] paramInstances = new Object[params.length];

                // Resolve each constructor parameter
                for (int i = 0; i < params.length; i++) {
                    paramInstances[i] = resolve(params[i].getType());
                }

                // Instantiate the class with constructor injection
                instance = (T) constructor.newInstance(paramInstances);
                register(clazz, instance);
            }
        }
        return instance;
    }
}
