package com.akivaliaho;

import com.akivaliaho.threed.NodeBuilder;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    public static final String APP_NAME = "Reitinhakualgoritmien visualisoija";
    private static final String GENEROI_GRAAFI = "Generoi graafi";
    private static final int ACCORDION_COLUMN_INDEX = 0;
    private static final int ACCORDION_ROW_INDEX = 1;
    private static final String PRELIMINARY_LABEL_TEXT = "Algoritmi: ";
    private static final int SUBSCENE_COLUMN_INDEX = 1;
    private static final int SUBSCENE_ROW_INDEX = 0;
    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private static final String ETSI_POLKU = "Etsi polku";
    private static final String TOGGLE_AXIS_VISIBILITY = "Näytä koordinaatisto";
    private NodeBuilder nodeBuilder;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting the graph route tool");
        log.debug("Showing JFX scene");
        GridPane gridPane = new GridPaneCreator().createGridPane();
        Scene scene = new Scene(gridPane, 800, 600);

        SubScene subScene = createSubScene(scene);
        nodeBuilder = new NodeBuilder(subScene);
        createStaticUIComponents(gridPane);

        //Generate threed axes and a default graph for the subscene
        scene.getStylesheets().add("/styles/styles.css");
        stage.setTitle(APP_NAME);
        stage.setScene(scene);

        stage.show();
    }

    private SubScene createSubScene(Scene scene) {
        StackPane pane = new StackPane();
        SubScene subScene = new SubScene(pane, 500, 500, true, SceneAntialiasing.DISABLED);
        ReadOnlyDoubleProperty width = ((GridPane) scene.getRoot()).widthProperty();
        ReadOnlyDoubleProperty height = ((GridPane) scene.getRoot()).heightProperty();

        setSubsceneProperties(subScene, width, height);
        ((GridPane) scene.getRoot()).add(subScene, SUBSCENE_COLUMN_INDEX, SUBSCENE_ROW_INDEX, 3, 4);
        return subScene;
    }

    private void setSubsceneProperties(SubScene subScene, ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height) {
        subScene.widthProperty().bind(width);
        subScene.heightProperty().bind(height);
        subScene.setPickOnBounds(true);
    }

    private void createStaticUIComponents(GridPane gridPane) {
        createLabels(gridPane);
        createAccordion(gridPane);
        createVBox(gridPane);
    }

    private void createVBox(GridPane scene) {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(createButtons());
        scene.add(vBox, 0, 2);
    }

    private List<Button> createButtons() {
        Button generoiGraafi = new Button(GENEROI_GRAAFI);
        Button etsiPolku = new Button(ETSI_POLKU);
        Button toggleAxis = new Button(TOGGLE_AXIS_VISIBILITY);
        generoiGraafi.addEventHandler(MouseEvent.MOUSE_CLICKED, new GenerateGraphHandler(nodeBuilder));
        etsiPolku.addEventHandler(MouseEvent.MOUSE_CLICKED, new FindPathHandler());
        toggleAxis.addEventHandler(MouseEvent.MOUSE_CLICKED, new ToggleAxisHandler(nodeBuilder));
        List<Button> objects = new ArrayList<>();
        objects.add(generoiGraafi);
        objects.add(etsiPolku);
        objects.add(toggleAxis);
        return objects;
    }

    private void createLabels(GridPane gridPane) {
        Label selectorLabel = new Label(PRELIMINARY_LABEL_TEXT);
        selectorLabel.setWrapText(true);
        gridPane.add(selectorLabel, 0, 0, 1, 1);
    }

    private void createAccordion(GridPane gridPane) {
        Accordion accordion = new Accordion();
        ObservableList<TitledPane> panes = accordion.getPanes();
        panes.addAll(createTitledPanes());
        gridPane.add(accordion, ACCORDION_COLUMN_INDEX, ACCORDION_ROW_INDEX);
    }

    private List<TitledPane> createTitledPanes() {
        TitledPane djikstra = new TitledPane("Djikstra", null);
        TitledPane floyd = new TitledPane("Floyd", null);
        List<TitledPane> panes = new ArrayList<>();
        panes.add(djikstra);
        panes.add(floyd);
        return panes;
    }


}
