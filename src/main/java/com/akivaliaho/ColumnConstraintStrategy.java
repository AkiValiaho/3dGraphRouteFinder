package com.akivaliaho;

import javafx.scene.layout.ColumnConstraints;

import static com.google.common.base.Preconditions.checkArgument;

class ColumnConstraintStrategy {
    ColumnConstraints createColumnConstraint(int i) {
        checkArgument(i > 0, "Given column constraint divider is negative");
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100 / i);
        return columnConstraints;
    }

    ColumnConstraints createColumnConstraint(double v) {
        checkArgument(v > 0, "Given column constraint is negative");
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(v);
        return columnConstraints;
    }


}
