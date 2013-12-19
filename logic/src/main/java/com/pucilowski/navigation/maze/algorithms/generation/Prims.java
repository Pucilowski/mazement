package com.pucilowski.navigation.maze.algorithms.generation;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Neighborship;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.util.ArrayList;

/**
 * Created by martin on 19/12/13.
 */
public class Prims extends Generator {


    ArrayList<Cell> visited = new ArrayList<Cell>();
    ArrayList<Neighborship> walls = new ArrayList<Neighborship>();

    public Prims(Grid grid) {
        super(grid);


    }

    public void start() {


        while (step()) {


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            grid.gen();


        }
    }

    public void addWalls(Cell cell) {
        for (Neighborship n : cell.neighborships) {
            if (n == null /*|| n.target.walls>0*/) continue;
            walls.add(n);
        }
    }

    public boolean step() {

        //DIR[] dirs = DIR.values();
        //Collections.shuffle(Arrays.asList(dirs));
        //for (DIR dir : dirs) {

        //System.out.println("cx: " + cx + " cy: " + cy);


        if (walls.isEmpty()) {
            Cell start = grid.cells[0][0];
            visited.add(start);
            addWalls(start);
        }


        //Collections.shuffle(walls, random);
        Neighborship wall = walls.get(0);

        if (visited.contains(wall.target)) {
            walls.remove(wall);
        } else {
            grid.connect(wall);
            visited.add(wall.target);
            addWalls(wall.target);
        }

        step.onStep(wall.source);

        return true;


    }


}
