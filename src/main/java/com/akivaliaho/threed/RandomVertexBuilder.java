package com.akivaliaho.threed;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RandomVertexBuilder extends RandomBuilder {
    private final List<Sphere> spheres;
    private final PhongMaterial cylinderMaterial;
    private final ExecutorService threadPool;

    RandomVertexBuilder(List<Sphere> spheres, PhongMaterial cylinderMaterial) {
        this.spheres = spheres;
        this.cylinderMaterial = cylinderMaterial;
        this.threadPool = Executors.newCachedThreadPool();
    }

    List<DirectionalCylinder> buildRandomVertexesBetweenEdges() {
        List<DirectionalCylinder> cylinders = new ArrayList<>();
        Deque<Sphere> sphereDeque = new ArrayDeque<>(spheres);
        buildBetweenEdges(cylinders, sphereDeque);
        return cylinders;
    }

    private void buildBetweenEdges(List<DirectionalCylinder> cylinders, Deque<Sphere> sphereDeque) {
        int poppedNumber = 0;
        while (!sphereDeque.isEmpty()) {
            Sphere pop = sphereDeque.pop();
            tryToCreateDirectionalCylinder(cylinders, pop, poppedNumber);
            poppedNumber++;
        }
    }

    private void tryToCreateDirectionalCylinder(List<DirectionalCylinder> cylinders, Sphere pop, int poppedNumber) {
        List<DirectionalCylinder> list = new ArrayList<>();
        for (int i = 0; i < spheres.size(); i++) {
            //Avoid cycles
            if (i == poppedNumber) {
                continue;
            }
            if (yesOrNo()) {
                DirectionalCylinder directionalCylinder = buildCylinderToPopped(spheres.get(i), pop);
                list.add(directionalCylinder);
            }
        }
        cylinders.addAll(list);
    }

    private DirectionalCylinder buildCylinderToPopped(Sphere to, Sphere from) {
        DirectionalCylinder directionalCylinder = new DirectionalCylinder(1, 100);
        translateCylinderToFrom(from, directionalCylinder);


        threadPool.submit(new GenericRotator(to, directionalCylinder));
        directionalCylinder.setMaterial(cylinderMaterial);
        return directionalCylinder;
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
