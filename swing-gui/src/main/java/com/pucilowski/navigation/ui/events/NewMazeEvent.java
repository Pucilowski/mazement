package com.pucilowski.navigation.ui.events;

import com.pucilowski.navigation.ui.swing.dialogs.MazeType;

/**
 * Created by martin on 24/12/13.
 */
public class NewMazeEvent extends Event {

    private final MazeType type;
    private final int width;
    private final int height;

    public NewMazeEvent(MazeType type, int width, int height) {
        this.type=type;
        this.width=width;
        this.height=height;
    }

    @Override
    public String toString() {
        return "NewMazeEvent=["+type+", "+width+", "+height+"]";
    }

}
