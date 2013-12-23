package com.pucilowski.navigation.maze.algorithm;

import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Grid;

/**
 * Created by martin on 10/12/13.
 */
public abstract class Pathfinder<M extends CellMeta> extends Algorithm<M> {

    public final Cell start;
    public final Cell goal;

    public Pathfinder(Grid grid, Cell start, Cell goal) {
        super(grid);

        this.start = start;
        this.goal = goal;
    }

}
