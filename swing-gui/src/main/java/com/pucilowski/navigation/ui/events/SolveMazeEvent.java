package com.pucilowski.navigation.ui.events;

import com.pucilowski.navigation.ui.swing.dialogs.enums.MazePathfinder;

/**
 * Created by martin on 24/12/13.
 */
public class SolveMazeEvent extends Event {

    public final MazePathfinder gen;

    public SolveMazeEvent(MazePathfinder gen) {
        this.gen=gen;
    }

    @Override
    public String toString() {
        return "SolveMazeEvent=["+gen+"]";
    }

}
