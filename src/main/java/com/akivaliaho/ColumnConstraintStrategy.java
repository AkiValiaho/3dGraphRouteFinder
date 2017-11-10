package com.akivaliaho;

import javafx.scene.layout.ColumnConstraints;

class ColumnConstraintStrategy {
    ColumnConstraints createColumnConstraint(int i) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100 / i);
        return columnConstraints;
    }

    ColumnConstraints createColumnConstraint(double v) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(v);
        return columnConstraints;
    }


}
