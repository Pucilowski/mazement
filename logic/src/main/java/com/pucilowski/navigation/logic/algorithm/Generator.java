package com.pucilowski.navigation.logic.algorithm;

import com.pucilowski.navigation.logic.algorithm.generation.misc.StepListener;
import com.pucilowski.navigation.logic.grid.Cell;
import com.pucilowski.navigation.logic.grid.Grid;

import java.util.Random;

/**
 * Created by martin on 19/12/13.
 */
public abstract class Generator<M extends CellMeta> extends Algorithm<M> {

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
