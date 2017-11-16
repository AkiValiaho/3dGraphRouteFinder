package com.akivaliaho.threed;

import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.shape.Sphere;

class GenericRotator implements Runnable {
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

    @Override
    public void run() {
        rotateCylinderTowardsTo();
    }

    private void rotateCylinderTowardsTo() {
        Point3D toPoint = new Point3D(to.getTranslateX(), to.getTranslateY(), to.getTranslateZ());
        Point3D cylinderPoint = new Point3D(directionalCylinder.getTranslateX(), directionalCylinder.getTranslateY(), directionalCylinder.getTranslateZ());
        double distance = toPoint.distance(
                cylinderPoint
        );

        directionalCylinder.setHeight(distance);
        combinedMatrixRotateNode(directionalCylinder, getPitch(toPoint, cylinderPoint), 0, 0);

    }

    private double getPitch(Point3D toPoint, Point3D cylinderPoint) {
        return Math.asin(Math.abs(toPoint.getY()) / toPoint.distance(0, 0, 0)) * 180 / Math.PI;
    }


    private void combinedMatrixRotateNode(Node n, double pitch, double yaw, double roll) {
        //The combined X, Y and Z rotation matrix
        double A11 = Math.cos(pitch) * Math.cos(roll);
        double A12 = Math.cos(yaw) * Math.sin(pitch) + Math.cos(pitch) * Math.sin(yaw) * Math.sin(roll);
        double A13 = Math.sin(pitch) * Math.sin(yaw) - Math.cos(pitch) * Math.cos(yaw) * Math.sin(roll);
        double A21 = -Math.cos(roll) * Math.sin(pitch);
        double A22 = Math.cos(pitch) * Math.cos(yaw) - Math.sin(pitch) * Math.sin(yaw) * Math.sin(roll);
        double A23 = Math.cos(pitch) * Math.sin(yaw) + Math.cos(yaw) * Math.sin(pitch) * Math.sin(roll);
        double A31 = Math.sin(roll);
        double A32 = -Math.cos(roll) * Math.sin(yaw);
        double A33 = Math.cos(yaw) * Math.cos(roll);

        //The angle can be computed from arccos(1/2{A11 + A22 + A33 -1)
        double d = Math.acos((A11 + A22 + A33 - 1d) / 2d);
        if (d != 0d) {
            double den = 2d * Math.sin(d);
            Point3D p = new Point3D((A32 - A23) / den, (A13 - A31) / den, (A21 - A12) / den);
            n.setRotationAxis(p);
            n.setRotate(Math.toDegrees(d));
        }
    }

}
