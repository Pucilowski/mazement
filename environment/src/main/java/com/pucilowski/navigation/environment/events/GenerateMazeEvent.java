package com.pucilowski.navigation.environment.events;

import com.pucilowski.navigation.environment.enums.MazeGenerator;

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
