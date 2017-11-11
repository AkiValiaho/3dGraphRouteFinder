package com.akivaliaho.threed;

import javafx.geometry.Point2D;

class ZRotator {
    private final DirectionalCylinder directionalCylinder;
    private final Point2D negativeYAxle;
    private final Point2D yAxle;

    ZRotator(DirectionalCylinder directionalCylinder) {
        this.directionalCylinder = directionalCylinder;
        yAxle = new Point2D(0, 1);
        negativeYAxle = new Point2D(0, -1);
    }

    double findAngleTowardsPoint(Point2D toPointXY) {
        setPerpendicularToAxle();
        Quadrant quadrant = findQuadrant(toPointXY);
        return 0;
    }

    private void setPerpendicularToAxle() {
        Quadrant quadrant = findQuadrant(new Point2D(directionalCylinder.getTranslateX(), directionalCylinder.getTranslateY()));
        bottomLeft(quadrant);
        bottomRight(quadrant);
        topLeft(quadrant);
        topRight(quadrant);
    }

    private void bottomLeft(Quadrant quadrant) {
        if (quadrant.getPointQuadrant() == Quadrant.QuadrantAlpha.BOTTOM_LEFT) {
            perpendularsmallerThanYAndX(directionalCylinder, negativeYAxle);
        }
    }

    private void perpendularsmallerThanYAndX(DirectionalCylinder directionalCylinder, Point2D negativeYAxle) {
        double angle = negativeYAxle.angle(directionalCylinder.getTranslateX(), directionalCylinder.getTranslateY());
        directionalCylinder.setRotate(180 - angle);
    }

    private void bottomRight(Quadrant quadrant) {
        if (quadrant.getPointQuadrant() == Quadrant.QuadrantAlpha.BOTTOM_RIGHT) {
            directionalCylinder.setRotate(180 + negativeYAxle.angle(directionalCylinder.getTranslateX(), directionalCylinder.getTranslateY()));
        }
    }

    private void topLeft(Quadrant quadrant) {
        if (quadrant.getPointQuadrant() == Quadrant.QuadrantAlpha.TOP_LEFT) {
            double angle = yAxle.angle(directionalCylinder.getTranslateX(), directionalCylinder.getTranslateY());
            directionalCylinder.setRotate(angle);
        }
    }

    private void topRight(Quadrant quadrant) {
        if (quadrant.getPointQuadrant() == Quadrant.QuadrantAlpha.TOP_RIGHT) {
            directionalCylinder.setRotate(180 - yAxle.angle(directionalCylinder.getTranslateX(), directionalCylinder.getTranslateY()));
        }
    }

    private Quadrant findQuadrant(Point2D toPointXY) {
        return new Quadrant(toPointXY);
    }


}
