package com.pucilowski.navigation.logic.process;

import com.pucilowski.navigation.logic.grid.Grid;

import java.util.Random;

/**
 * Created by martin on 19/12/13.
 */
public abstract class Generator<M extends CellMeta> extends Process<M> {

    public final Random random;


    public Generator(Grid grid) {
        super(grid);
        this.random = new Random();
    }


}
