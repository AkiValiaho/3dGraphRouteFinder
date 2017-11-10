package com.akivaliaho;

import javafx.scene.layout.RowConstraints;

class RowConstraintStrategy {
    RowConstraints createRowConstraint(int i) {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100 / i);
        return rowConstraints;
    }
}
