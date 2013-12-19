package com.pucilowski.navigation.maze.algorithms.generation;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Neighborship;
import com.pucilowski.navigation.maze.model.grid.Grid;
import com.pucilowski.navigation.maze.model.grid.SquareGrid;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by martin on 19/12/13.
 */
public class StackDFS extends AbstractGenerator {



    Stack<Cell> visited = new Stack<Cell>();



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

        //DIR[] dirs = DIR.values();
        //Collections.shuffle(Arrays.asList(dirs));
        //for (DIR dir : dirs) {

        //System.out.println("cx: " + cx + " cy: " + cy);

        if (visited.empty()) return false;

        Cell current = visited.peek();

        //System.out.println("step: " + current);

        Neighborship[] neighborships = current.neighborships;
        Collections.shuffle(Arrays.asList(neighborships), random);

        for (int i = 0; i < neighborships.length; i++) {
            //Cell adjacent = grid.getAdjacentTile(current, i);
            Neighborship neigh = neighborships[i];
            if (neigh == null) continue;

            //int nx = cx + dir.dx;
            //int ny = cy + dir.dy;
            int nx = neigh.target.x;
            int ny = neigh.target.y;

            if ((grid.cells[nx][ny].walls == 0)) {

                grid.connect(neigh);

                visited.add(neigh.target);
                neigh.target.depth = current.depth;

                return true;
                //maze[cx][cy] |= dir.bit;
                //maze[nx][ny] |= dir.opposite.bit;
            }

        }


        visited.pop();

        return true;


    }
}
