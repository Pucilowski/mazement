package com.pucilowski.navigation.logic.process.pathfinding;

import com.pucilowski.navigation.logic.process.CellMeta;
import com.pucilowski.navigation.logic.grid.Cell;

/**
 * Created by martin on 22/12/13.
 */
public class SearchMeta extends CellMeta {

    public double gScore = 0;
    public double fScore = 0;

    Cell parent;
    public int order;

    public SearchMeta(Cell cell) {
        super(cell);
    }
}
