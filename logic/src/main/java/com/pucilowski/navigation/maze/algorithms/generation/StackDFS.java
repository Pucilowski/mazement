package com.pucilowski.navigation.maze.algorithms.generation;

import com.pucilowski.navigation.maze.algorithms.State;
import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Edge;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.util.*;

/**
 * Created by martin on 19/12/13.
 */
public class StackDFS extends Generator {

    LinkedList<Cell> visited = new LinkedList<Cell>();

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

        Edge[] edges = current.getEdges();
        Collections.shuffle(Arrays.asList(edges), random);

        for (Edge edge : edges) {
            if (edge == null) continue;

            System.out.println("Neighbor: " + edge.target);

            if (edge.target.walls == 0) {
                grid.connect(edge);
                visited.push(edge.target);

                step.onStep(edge.target);

                return;
            }
        }

        visited.pop();
    }
}
