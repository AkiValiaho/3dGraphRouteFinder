package com.akivaliaho;

import com.akivaliaho.threed.NodeBuilder;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class GenerateGraphHandler implements EventHandler<MouseEvent> {

    private final NodeBuilder nodeBuilder;

    GenerateGraphHandler(NodeBuilder nodeBuilder) {
        this.nodeBuilder = nodeBuilder;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Generoi called");
        nodeBuilder.buildGraph();
    }
}
