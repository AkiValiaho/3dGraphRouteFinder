package com.akivaliaho;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class SphereEventHandler implements EventHandler<MouseEvent> {

    private final SubScene subScene;
    private List<Sphere> balls;

    public SphereEventHandler(SubScene subScene) {
        this.subScene = subScene;
    }

    @Override
    public void handle(MouseEvent event) {
        log.info("Clicked at x: {}, y: {}", event.getScreenX(), event.getSceneY());
        //Check if any of the balls is contained in the click event
        Sphere containingBall = getContainingBall(event.getScreenX(), event.getScreenY());
        //Change sphere color to green
        if (containingBall != null) {
            PhongMaterial greenMaterial = new PhongMaterial(Color.GREEN);
            if (((PhongMaterial) containingBall.getMaterial()).getDiffuseColor().equals(Color.GREEN)) {
                {
                    containingBall.setMaterial(new PhongMaterial(Color.RED));
                    return;
                }
            }
            containingBall.setMaterial(greenMaterial);
        }
    }

    private Sphere getContainingBall(double xCoordinate, double yCoordinate) {
        List<Sphere> containingBalls = new ArrayList<>();
        for (Sphere ball : balls) {
            Bounds bounds = ball.localToScreen(ball.getBoundsInLocal());
            log.info("Ball local bounds x: {} y: {}", bounds.getMaxX(), bounds.getMaxY());
            if (bounds.contains(xCoordinate, yCoordinate)) {
                log.info("Ball hit");
               containingBalls.add(ball);
            }
        }
        if (containingBalls.size() > 1) {
            //TODO Find camera angle and get the closest one from z coordinates
            return containingBalls.get(0);
        } else {
            if (containingBalls.size() == 0) {
                return null;
            }
            return containingBalls.get(0);
        }
    }

    public void setBalls(List<Sphere> balls) {
        this.balls = balls;
    }
}
