package com.pucilowski.navigation.maze.algorithms.generation;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Neighborship;
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

        grid.gen();
    }

    private void explore(Cell current) {
        Neighborship[] neighborships = current.neighborships;
        Collections.shuffle(Arrays.asList(neighborships), random);

        for (int i = 0; i < neighborships.length; i++) {
            Neighborship neigh = neighborships[i];
            if (neigh == null) continue;

            int nx = neigh.target.x;
            int ny = neigh.target.y;

            if ((grid.cells[nx][ny].walls == 0)) {
                grid.connect(neigh);

                explore(neigh.target);
            }
        }
    }
}
