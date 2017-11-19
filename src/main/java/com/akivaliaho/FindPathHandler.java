package com.akivaliaho;

import com.akivaliaho.threed.NodeBuilder;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class FindPathHandler implements EventHandler<MouseEvent> {

    private final NodeBuilder nodeBuilder;

    FindPathHandler(NodeBuilder nodeBuilder) {
        this.nodeBuilder = nodeBuilder;
    }

    @Override
    public void handle(MouseEvent event) {
        nodeBuilder.getEdges();
    }
}
