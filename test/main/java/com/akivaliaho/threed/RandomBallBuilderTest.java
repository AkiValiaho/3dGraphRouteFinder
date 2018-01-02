package com.akivaliaho.threed;


import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.akivaliaho.BooleanUtils.assertNotEmpty;
import static org.mockito.Mockito.mock;

public class RandomBallBuilderTest {

    private RandomBallBuilder randomBallBuilder;

    @Before
    public void setUp() throws Exception {
        CoordinateConstraint coordinateConstraint = new CoordinateConstraint(300, 300, 300);
        this.randomBallBuilder = new RandomBallBuilder(coordinateConstraint, mock(SubScene.class));
    }

    @Test
    public void generateRandomNumberOfBalls() {
        Transformer transformer = new Transformer();
        List<Sphere> spheres = randomBallBuilder.generateRandomNumberOfBalls(transformer, new PhongMaterial(Color.RED));
        assertNotEmpty(spheres);
    }


}