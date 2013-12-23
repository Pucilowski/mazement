package com.pucilowski.navigation.maze.algorithm.pathfinding;

import com.pucilowski.navigation.maze.algorithm.CellMeta;
import com.pucilowski.navigation.maze.grid.Cell;

/**
 * Created by martin on 22/12/13.
 */
public class SearchMeta extends CellMeta {

    double gScore = 0;
    double fScore = 0;

    Cell parent;
    public int order;

    public SearchMeta(Cell cell) {
        super(cell);
    }
}
