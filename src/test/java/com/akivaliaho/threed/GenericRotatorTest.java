package com.akivaliaho.threed;

import javafx.scene.shape.Sphere;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenericRotatorTest {

    private Sphere to;
    private Sphere from;
    private GenericRotator genericRotator;
    private DirectionalCylinder directionalCylinder;

    @Before
    public void setUp() throws Exception {
        to = new Sphere(10, 1);
        to.setTranslateY(100);
        to.setTranslateX(100);
        to.setTranslateZ(100);
        from = new Sphere(10, 1);
        directionalCylinder = new DirectionalCylinder(100, 100, from, to);
        this.genericRotator = new GenericRotator(to, directionalCylinder);
    }

    @Test
    public void rotateCylinderTowardsTo() {
        this.genericRotator.rotateCylinderTowardsTo();
        assertTranslates();
        //Check distance matches
        Clean3DPoint to = new Clean3DPoint(this.to);
        Clean3DPoint from = new Clean3DPoint(this.from);
        double distance = to.distance(from);
        assertEquals(distance, directionalCylinder.getHeight(), 0);
    }

    private void assertTranslates() {
        assertEquals(directionalCylinder.getTranslateX(), 50, 0.0);
        assertEquals(directionalCylinder.getTranslateY(), 50, 0.0);
        assertEquals(directionalCylinder.getTranslateZ(), 50, 0.0);
    }
}