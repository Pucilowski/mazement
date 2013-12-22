package com.pucilowski.navigation.maze.generation;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Edge;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by martin on 19/12/13.
 */
public class RecursiveDFS extends Generator {

    public RecursiveDFS(Grid grid) {
        super(grid);
    }

    @Override
    public void start() {


    }

    @Override
    public void step() {
        Cell cell = grid.cells[0][0];

        explore(cell);
    }

    private void explore(Cell current) {
        Edge[] edges = current.edges;
        Collections.shuffle(Arrays.asList(edges), random);

        for (int i = 0; i < edges.length; i++) {
            Edge neigh = edges[i];
            if (neigh == null) continue;

            if (neigh.target.walls == 0) {
                grid.connect(neigh);

                explore(neigh.target);
            }
        }
    }
}
