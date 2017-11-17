package com.akivaliaho;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class CheckBallEvent extends MouseEvent {
    public static EventType<CheckBallEvent> checkBallEventEventType = new EventType<>("ALTERNATIVE_MOUSE_EVENT");
    private final MouseEvent mouseEvent;

    public CheckBallEvent(MouseEvent me) {
        super(checkBallEventEventType, me.getSceneX(), me.getSceneY()
                , me.getScreenX(), me.getScreenY(), me.getButton(), me.getClickCount(),
                me.isShiftDown(), me.isControlDown(), me.isAltDown(), me.isMetaDown(), me.isPrimaryButtonDown(), me.isMiddleButtonDown(), me.isSecondaryButtonDown(), me.isSynthesized(), me.isPopupTrigger(), me.isStillSincePress(), me.getPickResult());
        this.mouseEvent = me;
    }

}
