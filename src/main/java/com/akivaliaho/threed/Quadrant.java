package com.akivaliaho.threed;

import javafx.geometry.Point2D;

class Quadrant {

    private QuadrantAlpha pointQuadrant;

    Quadrant(Point2D toPointXY) {
        if (toPointXY.getY() < 0) {
            if (toPointXY.getX() < 0) {
                pointQuadrant = QuadrantAlpha.BOTTOM_LEFT;
            } else {
                pointQuadrant = QuadrantAlpha.BOTTOM_RIGHT;
            }
        } else if (toPointXY.getX() < 0) {
            pointQuadrant = QuadrantAlpha.TOP_LEFT;
        } else {
            pointQuadrant = QuadrantAlpha.TOP_RIGHT;
        }
    }

    public QuadrantAlpha getPointQuadrant() {
        return pointQuadrant;
    }

    enum QuadrantAlpha {
        BOTTOM_RIGHT, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT
    }
}
