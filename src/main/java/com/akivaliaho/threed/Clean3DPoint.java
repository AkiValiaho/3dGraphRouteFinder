package com.akivaliaho.threed;

import javafx.geometry.Point3D;
import javafx.scene.shape.Shape3D;

public class Clean3DPoint extends Point3D {
    public Clean3DPoint(Shape3D shape) {
        super(shape.getTranslateX(), shape.getTranslateY(), shape.getTranslateZ());
    }
}
