package com.akivaliaho;

import java.util.Collection;

public class TestUtils {
    public static void assertNotEmpty(Collection<?> collection) {
        if (!notEmpty(collection)) {
            throw new IllegalArgumentException("Collection is not empty");
        }
    }

    private static boolean notEmpty(Collection<?> collection) {
        return !collection.isEmpty();
    }
}
