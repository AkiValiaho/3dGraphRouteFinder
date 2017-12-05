package com.akivaliaho.threed;

import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

public class DirectionalCylinder extends Cylinder {
    private final Sphere fromEdge;
    private final Sphere toEdge;

    public DirectionalCylinder(double radius, double height, Sphere from, Sphere to) {
        super(radius, height);
        this.fromEdge = from;
        this.toEdge = to;
    }
}
