package com.akivaliaho.threed;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RandomBallBuilder {

    private static final int CONFLICT_CONSTANT = 10;
    private final PhongMaterial redMaterial;
    private final CoordinateConstraint coordinateConstraint;
    private Random random;

    RandomBallBuilder(CoordinateConstraint coordinateConstraint) {
        this.coordinateConstraint = coordinateConstraint;
        redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
    }

    List<Sphere> generateRandomNumberOfBalls(Transformer edgeTransformer) {

        List<Sphere> sphereList = new ArrayList<>();
        random = new Random();
        int i = random.nextInt(10) + 2;
        for (int j = 0; j < i; j++) {
            Sphere sphere = new Sphere(10);
            findNonCollidingPlace(sphere, sphereList);
            sphere.setMaterial(redMaterial);
            sphereList.add(sphere);
        }
        return sphereList;
    }

    private void findNonCollidingPlace(Sphere sphere, List<Sphere> sphereList) {
        setRandomTranslates(sphere);
        while (conflicts(sphere, sphereList)) {
            setRandomTranslates(sphere);
        }
    }

    private void setRandomTranslates(Sphere sphere) {

        RandomTranslates randomTranslates = new RandomTranslates().invoke();
        sphere.setTranslateY(randomTranslates.getyTranslate());
        sphere.setTranslateX(randomTranslates.getxTranslate());
        sphere.setTranslateZ(randomTranslates.getzTranslate());
    }


    private boolean conflicts(Sphere sphere, List<Sphere> sphereList) {
        return sphereList.stream()
                .anyMatch(listInstance -> translateConflict(listInstance, sphere));
    }

    private boolean translateConflict(Sphere listInstance, Sphere sphere) {
        double instanceTranslateZ = listInstance.getTranslateZ();
        double instanceTranslateX = listInstance.getTranslateX();
        double instanceTranslateY = listInstance.getTranslateY();

        double translateZ = sphere.getTranslateZ();
        double translateX = sphere.getTranslateX();
        double translateY = sphere.getTranslateY();

        return checkForConflict(instanceTranslateZ, instanceTranslateY, instanceTranslateX, translateZ, translateY, translateX);
    }

    private boolean checkForConflict(double instanceTranslateZ, double instanceTranslateY, double instanceTranslateX, double translateZ, double translateY, double translateX) {
        return Math.abs(instanceTranslateZ - translateZ) < 5 || Math.abs(instanceTranslateY - translateY) < 5 || Math.abs(instanceTranslateX - translateX) < 5;
    }


    private class RandomTranslates {
        private double yTranslate;
        private double xTranslate;
        private double zTranslate;

        double getyTranslate() {
            return translate(yTranslate);
        }

        private double translate(double translate) {
            return checkForFlip() ? -translate : translate;
        }

        private boolean checkForFlip() {
            return random.nextInt(10) < 5;
        }

        double getxTranslate() {
            return translate(xTranslate);
        }

        double getzTranslate() {
            return translate(zTranslate);
        }

        RandomTranslates invoke() {
            yTranslate = random.nextDouble() * coordinateConstraint.getMaxY();
            xTranslate = random.nextDouble() * coordinateConstraint.getMaxX();
            zTranslate = random.nextDouble() * coordinateConstraint.getMaxZ();
            return this;
        }
    }
}
