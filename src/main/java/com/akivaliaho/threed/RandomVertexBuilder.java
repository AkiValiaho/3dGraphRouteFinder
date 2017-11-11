package com.akivaliaho.threed;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

class RandomVertexBuilder extends RandomBuilder {
    private final List<Sphere> spheres;
    private final PhongMaterial cylinderMaterial;

    RandomVertexBuilder(List<Sphere> spheres, PhongMaterial cylinderMaterial) {
        this.spheres = spheres;
        this.cylinderMaterial = cylinderMaterial;
    }

    List<DirectionalCylinder> buildRandomVertexesBetweenEdges() {
        List<DirectionalCylinder> cylinders = new ArrayList<>();
        Deque<Sphere> sphereDeque = new ArrayDeque<>(spheres);
        buildBetweenEdges(cylinders, sphereDeque);
        return cylinders;
    }

    private void buildBetweenEdges(List<DirectionalCylinder> cylinders, Deque<Sphere> sphereDeque) {
        while (!sphereDeque.isEmpty()) {
            Sphere pop = sphereDeque.pop();
            tryToCreateDirectionalCylinder(cylinders, pop);
        }
    }

    private void tryToCreateDirectionalCylinder(List<DirectionalCylinder> cylinders, Sphere pop) {
        cylinders.addAll(spheres.stream()
                .filter(sphere -> yesOrNo())
                .map(sphere -> buildCylinderToPopped(sphere, pop))
                .collect(Collectors.toList()));
    }

    private DirectionalCylinder buildCylinderToPopped(Sphere to, Sphere from) {
        DirectionalCylinder directionalCylinder = new DirectionalCylinder(5, 30);
        translateCylinderToFrom(from, directionalCylinder);

        rotateCylinderTowardsTo(to, directionalCylinder);
        directionalCylinder.setMaterial(cylinderMaterial);
        return directionalCylinder;
    }

    private void rotateCylinderTowardsTo(Sphere to, DirectionalCylinder directionalCylinder) {
        double toTranslateX = to.getTranslateX();
        double toTranslateY = to.getTranslateY();
        double toTranslateZ = to.getTranslateZ();
        //TODO

    }

    private void translateCylinderToFrom(Sphere from, DirectionalCylinder directionalCylinder) {
        double translateX = from.getTranslateX();
        double translateY = from.getTranslateY();
        double translateZ = from.getTranslateZ();
        directionalCylinder.setTranslateX(translateX);
        directionalCylinder.setTranslateY(translateY);
        directionalCylinder.setTranslateZ(translateZ);
    }


}
