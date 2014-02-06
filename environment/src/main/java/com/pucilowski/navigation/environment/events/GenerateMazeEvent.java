package com.pucilowski.navigation.environment.events;

import com.pucilowski.navigation.environment.Environment;
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

    @Override
    public void handle(Environment env) {
        env.grid.reset();

        env.process = gen.newGenerator(env.grid);

        env.process.start();

    }
}
