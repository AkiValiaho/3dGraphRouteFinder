package com.akivaliaho.threed;

class CoordinateConstraint {
    private final double maxX;
    private final double maxY;
    private final double maxZ;

    CoordinateConstraint(double maxX, double maxY, double maxZ) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    double getMaxX() {
        return maxX;
    }

    double getMaxY() {
        return maxY;
    }

    double getMaxZ() {
        return maxZ;
    }
}
