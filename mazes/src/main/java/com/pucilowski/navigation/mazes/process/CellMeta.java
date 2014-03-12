package com.pucilowski.navigation.mazes.process;

import com.pucilowski.navigation.mazes.grid.Cell;

/**
 * Created by martin on 22/12/13.
 */
public class CellMeta {

    Cell cell;

    public int depth;

    public CellMeta(Cell cell) {
        this.cell = cell;
    }

}
