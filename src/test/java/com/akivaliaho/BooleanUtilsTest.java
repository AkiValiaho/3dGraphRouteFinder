package com.akivaliaho;

import org.junit.Test;

import java.util.Collections;

public class BooleanUtilsTest {


    @Test
    public void assertNotEmpty_collectionWithContent_shouldNotDoAnything() {
        BooleanUtils.assertNotEmpty(Collections.singletonList(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void assertNotEmpty_empty_shouldThrowIllegalException() {
        BooleanUtils.assertNotEmpty(Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void assertNotEmpty_null_shouldThrowIllegalException() {
        BooleanUtils.assertNotEmpty(null);
    }
}