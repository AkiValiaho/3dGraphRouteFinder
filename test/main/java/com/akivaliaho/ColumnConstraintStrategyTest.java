package com.akivaliaho;

import javafx.scene.layout.ColumnConstraints;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ColumnConstraintStrategyTest {


    @Test(expected = IllegalArgumentException.class)
    public void createColumnConstraintInteger_negativeInteger_shouldThrowException() {
        ColumnConstraintStrategy columnConstraintStrategy = new ColumnConstraintStrategy();
        ColumnConstraints columnConstraints = columnConstraintStrategy.createColumnConstraint(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createColumnConstraintDouble_negativeDouble_shouldThrowException() {
        ColumnConstraintStrategy columnConstraintStrategy = new ColumnConstraintStrategy();
        ColumnConstraints columnConstraint = columnConstraintStrategy.createColumnConstraint(-1.0);
    }

    @Test
    public void createColumnConstraintInteger_properInteger_shouldCreateColumnConstraint() {
        ColumnConstraintStrategy columnConstraintStrategy = new ColumnConstraintStrategy();
        ColumnConstraints columnConstraint = columnConstraintStrategy.createColumnConstraint(1);
        assertEquals(columnConstraint.getPercentWidth(), 100.0);
    }

    @Test
    public void createColumnConstraintDouble() {
        ColumnConstraintStrategy columnConstraintStrategy = new ColumnConstraintStrategy();
        ColumnConstraints columnConstraint = columnConstraintStrategy.createColumnConstraint(199.0);
        assertEquals(columnConstraint.getPercentWidth(), 199.0);
    }
}