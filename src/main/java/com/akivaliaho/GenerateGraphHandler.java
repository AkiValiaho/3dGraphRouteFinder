package com.akivaliaho;

import com.akivaliaho.threed.NodeBuilder;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class GenerateGraphHandler implements EventHandler<MouseEvent> {

    private final NodeBuilder nodeBuilder;

    GenerateGraphHandler(NodeBuilder nodeBuilder) {
        this.nodeBuilder = nodeBuilder;
    }

    @Override
    public void handle(MouseEvent event) {
        log.info("Generoi called");
        nodeBuilder.buildGraph();
    }
}
