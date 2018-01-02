package com.akivaliaho.threed;

import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class Clean3DPointTest {

    @Test
    public void create_normalXYZCoordinates_shouldCreateCleanPointWithTranslates() {
        Sphere sphere = new Sphere();
        setShapeTranslates(sphere);
        Clean3DPoint cleanPoint = new Clean3DPoint(sphere);
        assertEquals(cleanPoint.getX(), 100.0);
        assertEquals(cleanPoint.getY(), 100.0);
        assertEquals(cleanPoint.getZ(), 100.0);
    }

    private void setShapeTranslates(Shape3D shape3D) {
        shape3D.setTranslateX(100);
        shape3D.setTranslateY(100);
        shape3D.setTranslateZ(100);
    }
}