package com.akivaliaho;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CheckBallEventTest {

    @Test
    public void construct() {
        CheckBallEvent checkBallEvent = new CheckBallEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 1, 1, 1, 1, MouseButton.PRIMARY, 1, true, false, false, false, false
                , false, false, false, false, false, null));
        assertEquals(checkBallEvent.getScreenX(), 1, 0);
    }
}