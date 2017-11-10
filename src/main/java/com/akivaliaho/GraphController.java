package com.akivaliaho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphController {
    private static final Logger log = LoggerFactory.getLogger(GraphController.class);

    @FXML
    private Label messageLabel;
    @FXML
    private Accordion accordion;


    public void uusiGraafi(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize() {

    }

    public void etsiPolku(ActionEvent actionEvent) {
        if (expandedPaneSelected()) {
            messageLabel.setText("Etsitään polkua käyttäen algoritmia: " + accordion.getExpandedPane().getText());
            accordion.setExpandedPane(null);
        }
    }

    private boolean expandedPaneSelected() {
        if (accordion.getExpandedPane() == null) {
            messageLabel.setText("Valitse haluamasi algoritmi!");
            return false;
        }
        return true;
    }
}
