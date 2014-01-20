package com.pucilowski.navigation.logic.process;

import com.pucilowski.navigation.logic.process.pathfinding.SearchMeta;
import com.pucilowski.navigation.logic.grid.Cell;
import com.pucilowski.navigation.logic.grid.Grid;

/**
 * Created by martin on 10/12/13.
 */
public abstract class Pathfinder<M extends CellMeta> extends Process<M> {

    public final Cell start;
    public final Cell goal;

    // result
    public Cell[] path = null;


    public Pathfinder(Grid grid, Cell start, Cell goal) {
        super(grid);

        this.start = start != null ? start : grid.cells[0][0];
        this.goal = goal != null ? goal : grid.cells[grid.width - 1][grid.height - 1];


    }

    public Pathfinder(Grid grid) {
        this(grid, null, null);
    }

    @Override
    public M newMeta(Cell cell) {
        return (M) new SearchMeta(cell);
    }
}

