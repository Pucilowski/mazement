package com.pucilowski.navigation.maze.algorithm.generation.misc;

import com.pucilowski.navigation.maze.grid.Cell;

/**
* Created by martin on 19/12/13.
*/
public interface StepListener {
    void onStep(Cell cell);
}
