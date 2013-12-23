package com.pucilowski.navigation.maze.algorithm;

import com.pucilowski.navigation.maze.algorithm.generation.misc.StepListener;
import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Grid;

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


    public Generator(Grid grid) {
        super(grid);
        this.random = new Random();
    }


}
