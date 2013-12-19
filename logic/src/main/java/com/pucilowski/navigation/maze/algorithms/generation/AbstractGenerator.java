package com.pucilowski.navigation.maze.algorithms.generation;

import com.pucilowski.navigation.maze.algorithms.generation.misc.StepListener;
import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.util.Random;

/**
 * Created by martin on 19/12/13.
 */
public abstract class AbstractGenerator   {

    public final Grid grid;
    public final Random random;

    public StepListener step = new StepListener() {
        @Override
        public void onStep(Cell cell) {

        }
    };

    State state = State.WORKING;

    public AbstractGenerator(Grid grid) {
        this.grid = grid;
        this.random = new Random();
    }

    public abstract void start();


    public enum State {
        WORKING,
        FAILED,
        SUCCESS
    }

}
