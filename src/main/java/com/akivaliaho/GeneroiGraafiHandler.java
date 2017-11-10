package com.akivaliaho;

import com.akivaliaho.threed.NodeBuilder;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class GeneroiGraafiHandler implements EventHandler<MouseEvent> {

    private final NodeBuilder nodeBuilder;

    GeneroiGraafiHandler(NodeBuilder nodeBuilder) {
        this.nodeBuilder = nodeBuilder;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Generoi called");
        nodeBuilder.buildGraph();
    }
}
