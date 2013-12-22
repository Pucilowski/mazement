package com.pucilowski.navigation.maze.pathfinding;

import com.pucilowski.navigation.maze.algorithm.Algorithm;
import com.pucilowski.navigation.maze.algorithm.CellMeta;
import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.pathfinding.model.Graph;
import com.pucilowski.navigation.maze.pathfinding.model.Vertex;
import com.pucilowski.navigation.maze.model.grid.Grid;

/**
 * Created by martin on 10/12/13.
 */
public abstract class Pathfinder<M extends CellMeta> extends Algorithm<M> {

    final Cell start;
    final Cell goal;

    public Pathfinder(Grid grid, Cell start, Cell goal) {
        super(grid);

        this.start = start;
        this.goal = goal;
    }

}
