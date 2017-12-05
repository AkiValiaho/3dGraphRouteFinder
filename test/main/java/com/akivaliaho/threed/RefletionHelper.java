package com.akivaliaho.threed;

import java.lang.reflect.Field;

/**
 * Created by Aki on 5.12.2017.
 */
class RefletionHelper {
    static <E> E getField(String fieldName, Object instantiatedObject) throws NoSuchFieldException, IllegalAccessException {
        final Field declaredField = instantiatedObject.getClass().getDeclaredField(fieldName);
        return (E) declaredField.get(instantiatedObject);
    }
}
