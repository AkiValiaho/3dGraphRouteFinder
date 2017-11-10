package com.akivaliaho;

import javafx.collections.ObservableList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.ConstraintsBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;

class GridPaneCreator {
    GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        createGridConstraints(gridPane);
        return gridPane;
    }

    private void createGridConstraints(GridPane gridPane) {
        ObservableList<RowConstraints> rowConstraints = gridPane.getRowConstraints();
        rowConstraints.addAll(createDefaultDistributionConstraint(4, Constraint.ROW));
        ObservableList<ColumnConstraints> columnConstraints = gridPane.getColumnConstraints();
        columnConstraints.addAll(createDefaultDistributionConstraint(2, Constraint.COLUMN));
    }

    private <T extends ConstraintsBase> List<T> createDefaultDistributionConstraint(int i, Constraint constraint) {
        List<T> list = new ArrayList<>();
        if (isColumnConstraint(constraint)) {
            return handleSpecialColumnConstraint(list);
        }
        for (int j = 0; j < i; j++) {
            list.add(createEqualDistributionConstraint(i, constraint));
        }
        return list;
    }

    private <T extends ConstraintsBase> List<T> handleSpecialColumnConstraint(List<T> list) {
        ColumnConstraintStrategy columnConstraintStrategy = new ColumnConstraintStrategy();
        list.add((T) columnConstraintStrategy.createColumnConstraint(25.0));
        list.add((T) columnConstraintStrategy.createColumnConstraint(75.0));
        return list;
    }

    private boolean isColumnConstraint(Constraint constraint) {
        return constraint.equals(Constraint.COLUMN);
    }

    private <T extends ConstraintsBase> T createEqualDistributionConstraint(int i, Constraint constraintType) {
        if (constraintType.equals(Constraint.ROW)) {
            return (T) new RowConstraintStrategy().createRowConstraint(i);
        } else {
            return (T) new ColumnConstraintStrategy().createColumnConstraint(i);
        }
    }
}
