package com.akivaliaho.threed;

import javafx.geometry.Point2D;

public class Axles {
    private final Point2D pointXY;
    private double yAxleAngle;
    private double negativeYAxleAngle;
    private double xAxleAngle;
    private double negativeXAxleAngle;
    private Point2D xAXle;
    private Point2D yAxle;
    private Point2D negativeYAxle;
    private Point2D negativeXAxle;

    Axles(Point2D pointXY) {
        this.pointXY = pointXY;
    }

    public double getyAxleAngle() {
        return yAxleAngle;
    }

    public void setyAxleAngle(double yAxleAngle) {
        this.yAxleAngle = yAxleAngle;
    }

    public double getNegativeYAxleAngle() {
        return negativeYAxleAngle;
    }

    public void setNegativeYAxleAngle(double negativeYAxleAngle) {
        this.negativeYAxleAngle = negativeYAxleAngle;
    }

    public double getxAxleAngle() {
        return xAxleAngle;
    }

    public void setxAxleAngle(double xAxleAngle) {
        this.xAxleAngle = xAxleAngle;
    }

    public double getNegativeXAxleAngle() {
        return negativeXAxleAngle;
    }

    public void setNegativeXAxleAngle(double negativeXAxleAngle) {
        this.negativeXAxleAngle = negativeXAxleAngle;
    }

    Axles findAngleToAxles() {
        yAxle = new Point2D(0, 1);
        xAXle = new Point2D(1, 0);
        negativeYAxle = new Point2D(0, -1);
        negativeXAxle = new Point2D(-1, 0);

        yAxleAngle = yAxle.angle(pointXY.getX(), pointXY.getY());
        negativeYAxleAngle = negativeYAxle.angle(pointXY.getX(), pointXY.getY());
        xAxleAngle = xAXle.angle(pointXY.getX(), pointXY.getY());
        negativeXAxleAngle = negativeXAxle.angle(pointXY.getX(), pointXY.getY());
        return this;
    }

    int getQuadrant() {
        if (pointXY.getY() < 0) {
            if (pointXY.getX() < 0) {
                return 3;
            }
            return 4;
        } else if (pointXY.getX() < 0) {
            return 2;
        } else {
            return 1;
        }
    }
}


    