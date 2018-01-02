package com.akivaliaho.threed;

public class RandomTranslates extends RandomBuilder {
    private final CoordinateConstraint coordinateConstraint;

    public RandomTranslates(CoordinateConstraint coordinateConstraint) {
        this.coordinateConstraint = coordinateConstraint;
    }

    double getyTranslate() {
        return translate(nextDouble() * coordinateConstraint.getMaxY());
    }

    private double translate(double translate) {
        return checkForFlip() ? -translate : translate;
    }

    private boolean checkForFlip() {
        return yesOrNo();
    }

    double getxTranslate() {
        return translate(nextDouble() * coordinateConstraint.getMaxX());
    }

    double getzTranslate() {
        return translate(nextDouble() * coordinateConstraint.getMaxZ());
    }


}

