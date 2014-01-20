package com.pucilowski.navigation.environment.events;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.environment.enums.MazePathfinder;

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

    @Override
    public void handle(Environment env) {
        env.process = gen.newPathfinder(env.grid);

        env.process.start();
    }
}
