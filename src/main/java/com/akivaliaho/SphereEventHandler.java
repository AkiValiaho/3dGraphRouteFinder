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
    private List<Sphere> spheres;

    public SphereEventHandler(SubScene subScene, List<Sphere> spheres) {
        this.subScene = subScene;
        this.spheres = spheres;
        //Add event handler to the spheres
        addEventHandler(spheres);
    }

    private void addEventHandler(List<Sphere> spheres) {
        spheres.stream()
                .forEach(sphere -> sphere.addEventHandler(CheckBallEvent.checkBallEventEventType, this));
    }

    @Override
    public void handle(MouseEvent event) {
        log.info("Clicked at x: {}, y: {}", event.getScreenX(), event.getSceneY());
        //Check if any of the spheres is contained in the click event
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
        for (Sphere sphere : spheres) {
            Bounds bounds = sphere.localToScreen(sphere.getBoundsInLocal());
            log.info("Ball local bounds x: {} y: {}", bounds.getMaxX(), bounds.getMaxY());
            if (bounds.contains(xCoordinate, yCoordinate)) {
                log.info("Ball hit at X: {}, Y:  {}", xCoordinate, yCoordinate);
                containingBalls.add(sphere);
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
}
