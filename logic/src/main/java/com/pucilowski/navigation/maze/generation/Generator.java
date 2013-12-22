package com.pucilowski.navigation.maze.generation;

import com.pucilowski.navigation.maze.algorithm.Algorithm;
import com.pucilowski.navigation.maze.algorithm.State;
import com.pucilowski.navigation.maze.generation.misc.StepListener;
import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.util.Random;

/**
 * Created by martin on 19/12/13.
 */
public abstract class Generator extends Algorithm {

    public final Random random;

    public StepListener step = new StepListener() {
        @Override
        public void onStep(Cell cell) {

        }
    };

    public State state = State.WORKING;

    public Generator(Grid grid) {
        super(grid);
        this.random = new Random();
    }





}
