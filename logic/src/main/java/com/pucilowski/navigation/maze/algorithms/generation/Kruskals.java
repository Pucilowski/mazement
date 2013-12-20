package com.pucilowski.navigation.maze.algorithms.generation;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Edge;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by martin on 19/12/13.
 */
public class Kruskals extends Generator {


    ArrayList<Cell> visited = new ArrayList<Cell>();
    ArrayList<Edge> walls = new ArrayList<Edge>();

    public Kruskals(Grid grid) {
        super(grid);
    }

    public void start() {

        Cell start = grid.cells[0][0];
        visited.add(start);
        addWalls(start);

    }

    public void addWalls(Cell cell) {
        for (Edge n : cell.edges) {
            if (n == null /*|| n.target.walls>0*/) continue;
            walls.add(n);
        }
    }

    @Override
    public void step() {

        if(walls.isEmpty()) {
            state = State.SUCCESS;
            return;
        }

        Collections.shuffle(walls, random);
        Edge wall = walls.get(0);

        if (visited.contains(wall.target)) {
            walls.remove(wall);
        } else {
            grid.connect(wall);
            visited.add(wall.target);
            addWalls(wall.target);
        }

        step.onStep(wall.source);


    }


}
