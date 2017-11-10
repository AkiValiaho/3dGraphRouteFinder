package com.akivaliaho.threed;

import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;

import java.util.List;

public class NodeBuilder {
    private static final double CAMERA_INITIAL_DISTANCE = -1000;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    //Super-small clip to avoid that nasty non-transparent camera wall around x-axle
    private static final double CAMERA_NEAR_CLIP = Double.MIN_VALUE;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 250.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;
    final Transformer axisGroup = new Transformer();
    final Transformer moleculeGroup = new Transformer();
    final Transformer world = new Transformer();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Transformer cameraTransformer = new Transformer();
    final Transformer cameraTransformer2 = new Transformer();
    final Transformer cameraTransformer3 = new Transformer();
    private final SubScene subScene;
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    //The subscene from main class
    public NodeBuilder(SubScene scene) {
        this.subScene = scene;
        Group root = new Group();
        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);

        buildCamera(root);
        buildGraph();

        handleKeyboard(world);
        handleMouse(world);
        scene.setCamera(camera);
        ((StackPane) scene.getRoot()).getChildren().add(root);

    }

    private void buildCamera(Group root) {
        root.getChildren().add(cameraTransformer);
        cameraTransformer.getChildren().add(cameraTransformer2);
        cameraTransformer2.getChildren().add(cameraTransformer3);
        cameraTransformer3.getChildren().add(camera);
        cameraTransformer3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraTransformer.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraTransformer.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

    private void handleMouse(final Node root) {
        subScene.setOnMousePressed(me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        subScene.setOnMouseDragged(me -> {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);

            double modifier = 1.0;
            if (me.isPrimaryButtonDown()) {
                cameraTransformer.ry.setAngle(cameraTransformer.ry.getAngle() - mouseDeltaX * MOUSE_SPEED * ROTATION_SPEED);
                cameraTransformer.rx.setAngle(cameraTransformer.rx.getAngle() + mouseDeltaY * MOUSE_SPEED * ROTATION_SPEED);
            } else if (me.isSecondaryButtonDown()) {
                double z = camera.getTranslateZ();
                double newZ = z + mouseDeltaX * MOUSE_SPEED * 10;
                camera.setTranslateZ(newZ);
            } else if (me.isMiddleButtonDown()) {
                cameraTransformer2.t.setX(cameraTransformer2.t.getX() + mouseDeltaX * MOUSE_SPEED * TRACK_SPEED * 10);
                cameraTransformer2.t.setY(cameraTransformer2.t.getY() + mouseDeltaY * MOUSE_SPEED * TRACK_SPEED * 10);
            }
        });
    }

    private void handleKeyboard(final Node root) {
        subScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case Z:
                    cameraTransformer2.t.setX(0.0);
                    cameraTransformer2.t.setY(0.0);
                    camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                    cameraTransformer.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                    cameraTransformer.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                    break;
                case X:
                    axisGroup.setVisible(!axisGroup.isVisible());
                    break;
                case V:
                    moleculeGroup.setVisible(!moleculeGroup.isVisible());
                    break;
            }
        });
    }

    public void buildGraph() {
        //Kill all old edges and vertices from the graph
        world.getChildren().clear();
        buildAxes();
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial blackmaterial = new PhongMaterial();
        blackmaterial.setDiffuseColor(Color.BLACK);
        blackmaterial.setSpecularColor(Color.BLANCHEDALMOND);

        Transformer graphComponentGroup = new Transformer();
        Transformer graphTransformer = new Transformer();
        graphComponentGroup.getChildren().add(graphTransformer);

        Transformer edgeTransformer = new Transformer();
        graphTransformer.getChildren().add(edgeTransformer);
        List<Sphere> spheres = new RandomBallBuilder(new CoordinateConstraint(100, 100, 100)).generateRandomNumberOfBalls(edgeTransformer);
        edgeTransformer.getChildren().addAll(spheres);
        edgeTransformer.setTx(100);

        Transformer verticeTransformer = new Transformer();
        edgeTransformer.getChildren().add(verticeTransformer);

        world.getChildren().add(graphComponentGroup);
    }

    private void buildAxes() {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
        final Box yAxis = new Box(1, AXIS_LENGTH, 1);
        final Box zAxis = new Box(1, 1, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(false);
        world.getChildren().addAll(axisGroup);
    }

}
