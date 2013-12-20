package com.pucilowski.navigation.maze.algorithms.generation;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Edge;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by martin on 19/12/13.
 */
public class StackDFS extends Generator {

    Stack<Cell> visited = new Stack<Cell>();

    public StackDFS(Grid grid) {
        super(grid);
    }

    @Override
    public void start() {
        visited.push(grid.cells[0][0]);


    }

    @Override
    public void step() {
        if (visited.isEmpty()) {
            System.out.println("|");
            state = State.SUCCESS;
            return;
        }

        Cell current = visited.peek();

        Edge[] edges = current.edges;
        Collections.shuffle(Arrays.asList(edges), random);

        for (Edge n : edges) {
            if (n == null) continue;

            //int nx = n.target.x;
            //int ny = n.target.y;

            if (n.target.walls == 0) {
                grid.connect(n);
                visited.push(n.target);

                step.onStep(n.target);

                return;
            }
        }

        visited.pop();

    }
}
