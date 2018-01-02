package com.akivaliaho.threed;

import com.akivaliaho.CheckBallEvent;
import com.akivaliaho.SphereEventHandler;
import javafx.scene.SubScene;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


class RandomBallBuilder extends RandomBuilder {

    private final SubScene subScene;
    private final RandomTranslates randomTranslates;

    RandomBallBuilder(SubScene subScene, RandomTranslates randomTranslates) {
        this.subScene = subScene;
        this.randomTranslates = randomTranslates;
    }

    List<Sphere> generateRandomNumberOfBalls(PhongMaterial ballMaterial) {
        checkNotNull(ballMaterial);
        List<Sphere> sphereList = new ArrayList<>();
        int i = nextInt(10) + 2;
        for (int j = 0; j < i; j++) {
            addGeneratedBallToList(ballMaterial, sphereList);
        }
        return sphereList;
    }

    private void addGeneratedBallToList(PhongMaterial ballMaterial, List<Sphere> sphereList) {
        Sphere sphere = new Sphere(10);
        findNonCollidingPlace(sphere, sphereList);
        sphere.setMaterial(ballMaterial);
        sphere.addEventHandler(CheckBallEvent.checkBallEventEventType, new SphereEventHandler(subScene));
        sphereList.add(sphere);
    }

    private void findNonCollidingPlace(Sphere sphere, List<Sphere> sphereList) {
        setRandomTranslates(sphere);
        while (conflicts(sphere, sphereList)) {
            setRandomTranslates(sphere);
        }
    }

    private void setRandomTranslates(Sphere sphere) {
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

}
