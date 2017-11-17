package com.akivaliaho;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import java.util.ArrayList;
import java.util.List;


public class SphereEventHandler implements EventHandler<MouseEvent> {

    private final SubScene subScene;
    private List<Sphere> balls;

    public SphereEventHandler(SubScene subScene) {
        this.subScene = subScene;
    }

    @Override
    public void handle(MouseEvent event) {
        //Check if any of the balls is contained in the click event
        Point3D point3D = new Point3D(event.getSceneX(), event.getSceneY(), 0);
        Point3D point3D1 = subScene.sceneToLocal(point3D);
        Sphere containingBall = getContainingBall(event.getSceneX(), event.getSceneY());
        //Change sphere color to green
        containingBall.setMaterial(new PhongMaterial(Color.GREEN));
    }

    private Sphere getContainingBall(double xCoordinate, double yCoordinate) {
        List<Sphere> containingBalls = new ArrayList<>();
        for (Sphere ball : balls) {
            if (ball.getLayoutBounds().contains(new Point2D(xCoordinate, yCoordinate))) {
                containingBalls.add(ball);
            }
        }
        if (containingBalls.size() > 1) {
            //TODO Find camera angle and get the closest one from z coordinates
            return containingBalls.get(0);
        } else {
            return containingBalls.get(0);
        }
    }

    public void setBalls(List<Sphere> balls) {
        this.balls = balls;
    }
}
