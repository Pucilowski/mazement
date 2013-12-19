package com.pucilowski.navigation.maze.algorithms.generation;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Neighborship;
import com.pucilowski.navigation.maze.model.grid.Grid;
import com.pucilowski.navigation.maze.model.grid.SquareGrid;

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

    public void display() {

        int x = grid.width;
        int y = grid.height;
        Cell[][] cells = grid.cells;


        for (int i = 0; i < y; i++) {
            // draw the north edge
            for (int j = 0; j < x; j++) {
                System.out.print((grid.cells[j][i].walls & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < x; j++) {
                System.out.print((grid.cells[j][i].walls & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < x; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
