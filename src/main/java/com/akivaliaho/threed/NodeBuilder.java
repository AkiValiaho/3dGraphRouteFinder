package com.akivaliaho.threed;

import com.akivaliaho.SphereEventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

import java.util.List;

public class NodeBuilder {
    private static final double CAMERA_INITIAL_DISTANCE = -1000;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    //Super-small clip to avoid that nasty non-transparent camera wall around x-axle
    private static final double CAMERA_NEAR_CLIP = Double.MIN_VALUE;
    private static final double CAMERA_FAR_CLIP = 10000000.0;
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
    private SphereEventHandler sphereEventHandler;
    private Transformer edgeTransformer;

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
            sphereEventHandler.handle(me);
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
        world.getChildren().add(axisGroup);
        buildAxes();
        final PhongMaterial redMaterial = createMaterial(Color.DARKRED, Color.RED);

        final PhongMaterial blackmaterial = createMaterial(Color.BLACK, Color.BLANCHEDALMOND);

        Transformer graphComponentGroup = new Transformer();
        Transformer graphTransformer = new Transformer();
        graphComponentGroup.getChildren().add(graphTransformer);

        edgeTransformer = new Transformer();
        graphTransformer.getChildren().add(edgeTransformer);
        graphComponentGroup.setDepthTest(DepthTest.ENABLE);

        List<Sphere> spheres = new RandomBallBuilder(subScene,
                new RandomTranslates(new CoordinateConstraint(100, 100, 100)))
                .generateRandomNumberOfBalls(redMaterial);
        sphereEventHandler = new SphereEventHandler(subScene, spheres);
        edgeTransformer.getChildren().addAll(spheres);
        edgeTransformer.setDepthTest(DepthTest.ENABLE);

        Transformer verticeTransformer = new Transformer();
        edgeTransformer.getChildren().add(verticeTransformer);

        world.getChildren().add(graphComponentGroup);
        RandomVertexBuilder randomVertexBuilder = new RandomVertexBuilder(spheres, blackmaterial, sphereEventHandler);
        List<DirectionalCylinder> cylinders = randomVertexBuilder.buildRandomVertexesBetweenEdges();
        verticeTransformer.getChildren().addAll(cylinders);
    }

    private void buildAxes() {
        final PhongMaterial redMaterial = createMaterial(Color.DARKRED, Color.RED);

        final PhongMaterial greenMaterial = createMaterial(Color.DARKGREEN, Color.GREEN);

        final PhongMaterial blueMaterial = createMaterial(Color.DARKBLUE, Color.BLUE);

        final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
        final Box yAxis = new Box(1, AXIS_LENGTH, 1);
        final Box zAxis = new Box(1, 1, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        Point3D xPoint = new Point3D(100, 0, 0);
        Label xAxisLabel = new Label("x");
        xAxisLabel.getTransforms().setAll(new Translate(xPoint.getX(), xPoint.getY()));
        axisGroup.getChildren().add(xAxisLabel);
        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
    }

    private PhongMaterial createMaterial(Color diffuseColor, Color specularColor) {
        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(diffuseColor);
        blueMaterial.setSpecularColor(specularColor);
        return blueMaterial;
    }

    public void toggleAxisVisibility() {
        if (axisGroup.isVisible()) {
            axisGroup.setVisible(false);
        } else {
            axisGroup.setVisible(true);
        }
    }
}
