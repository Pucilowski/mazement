package com.pucilowski.navigation.maze.algorithms.generation;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Neighborship;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by martin on 19/12/13.
 */
public class StackDFS extends Generator {

    LinkedList<Cell> visited = new LinkedList<Cell>();

    public StackDFS(Grid grid) {
        super(grid);
    }

    public void start() {
        visited.add(grid.cells[0][0]);

        while (step())  {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            grid.gen();

            step.onStep(visited.peek());
        }
    }

    public boolean step() {
        if (visited.isEmpty()) return false;

        Cell current = visited.peek();

        Neighborship[] neighborships = current.neighborships;
        Collections.shuffle(Arrays.asList(neighborships), random);

        for (int i = 0; i < neighborships.length; i++) {
            //Cell adjacent = grid.getNeighbor(current, i);
            Neighborship neigh = neighborships[i];
            if (neigh == null) continue;

         int nx = neigh.target.x;
            int ny = neigh.target.y;

            if ((grid.cells[nx][ny].walls == 0)) {

                grid.connect(neigh);

                visited.push(neigh.target);
                neigh.target.depth = current.depth;

                return true;
          }
        }

        visited.pop();

        return true;
    }
}
