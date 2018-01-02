package com.akivaliaho.threed;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class GenericRotator {
    private final DirectionalCylinder directionalCylinder;
    private final Point2D negativeYAxle;
    private final Point2D yAxle;
    private final Sphere to;

    GenericRotator(Sphere to, DirectionalCylinder directionalCylinder) {
        this.to = to;
        this.directionalCylinder = directionalCylinder;
        yAxle = new Point2D(0, 1);
        negativeYAxle = new Point2D(0, -1);
    }

    public void rotateCylinderTowardsTo() {
        Point3D toPoint = new Clean3DPoint(to);
        Point3D cylinderPoint = new Clean3DPoint(directionalCylinder);
        double distance = toPoint.distance(
                cylinderPoint
        );
        directionalCylinder.setHeight(distance);
        rotateCylinder(directionalCylinder, cylinderPoint, toPoint);
        System.out.println("Rotated");
    }

    private void rotateCylinder(Cylinder directionalCylinder, Point3D origin, Point3D target) {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D differenceVector = target.subtract(origin);
        double height = differenceVector.magnitude();

        Point3D mid = target.midpoint(origin);
        Translate moveToMid = new Translate(mid.getX(), mid.getY(), mid.getZ());

        Point3D rotationAxis = differenceVector.crossProduct(yAxis);
        double angle = Math.acos(differenceVector.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), rotationAxis);

        directionalCylinder.setHeight(height);

        directionalCylinder.getTransforms().addAll(rotateAroundCenter);
        directionalCylinder.setTranslateX(moveToMid.getX());
        directionalCylinder.setTranslateY(moveToMid.getY());
        directionalCylinder.setTranslateZ(moveToMid.getZ());
    }
}
