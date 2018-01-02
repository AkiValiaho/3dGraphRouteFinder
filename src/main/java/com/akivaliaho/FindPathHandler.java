package com.akivaliaho;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class FindPathHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        log.info("Find path handler called");
    }
}
