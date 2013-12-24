package com.pucilowski.navigation.ui.events;

import com.pucilowski.navigation.ui.swing.dialogs.enums.MazeGenerator;

/**
 * Created by martin on 24/12/13.
 */
public class GenerateMazeEvent extends Event {

    public final MazeGenerator gen;

    public GenerateMazeEvent(MazeGenerator gen) {
        this.gen=gen;
    }

    @Override
    public String toString() {
        return "GenerateMazeEvent=["+gen+"]";
    }

}
