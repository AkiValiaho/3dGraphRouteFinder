package com.akivaliaho;

import com.akivaliaho.threed.NodeBuilder;
import javafx.event.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToggleAxisHandler implements EventHandler<javafx.scene.input.MouseEvent> {

    private final NodeBuilder nodeBuilder;

    public ToggleAxisHandler(NodeBuilder nodeBuilder) {
        this.nodeBuilder = nodeBuilder;
    }

    @Override
    public void handle(javafx.scene.input.MouseEvent event) {
        log.info("Toggled axis visibility");
        nodeBuilder.toggleAxisVisibility();
    }
}
