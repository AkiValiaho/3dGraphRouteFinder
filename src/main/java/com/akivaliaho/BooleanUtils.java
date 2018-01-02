package com.akivaliaho;

import java.util.Collection;

public class BooleanUtils {

    public static void assertNotEmpty(Collection<?> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Collection is empty");
        }
    }

}
