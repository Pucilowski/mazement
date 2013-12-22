package com.pucilowski.navigation.maze.generation.misc;

import com.pucilowski.navigation.maze.model.Cell;

/**
* Created by martin on 19/12/13.
*/
public interface StepListener {
    void onStep(Cell cell);
}
