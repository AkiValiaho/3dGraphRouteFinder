package com.akivaliaho;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

class EtsiPolkuHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Etsi polku called");
    }
}
