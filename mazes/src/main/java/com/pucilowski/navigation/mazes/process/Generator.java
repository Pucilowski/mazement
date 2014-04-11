package com.pucilowski.navigation.mazes.process;

import com.pucilowski.navigation.mazes.grid.Grid;

import java.util.Random;

/**
 * Created by martin on 19/12/13.
 */
public abstract class Generator<M extends CellMeta> extends Task<M> {

    public final Random random;


    public Generator(Grid grid) {
        super(grid);
        this.random = new Random();
    }


}
