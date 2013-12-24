package com.pucilowski.navigation.ui.events;

import com.pucilowski.navigation.ui.swing.dialogs.enums.GridType;

/**
 * Created by martin on 24/12/13.
 */
public class NewGridEvent extends Event {

    public final GridType type;
    public final int width;
    public final int height;

    public NewGridEvent(GridType type, int width, int height) {
        this.type=type;
        this.width=width;
        this.height=height;
    }

    @Override
    public String toString() {
        return "NewGridEvent=["+type+", "+width+", "+height+"]";
    }

}
