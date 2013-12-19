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
public class RecursiveDFS extends AbstractGenerator {


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
        //DIR[] dirs = DIR.values();
        //Collections.shuffle(Arrays.asList(dirs));
        //for (DIR dir : dirs) {

        //System.out.println("cx: " + cx + " cy: " + cy);

        //Cell current;


        //for (int i = 0; i < adjacentTiles.length; i++) {

        //Cell[] adjacentTiles = grid.getNeighbours(current);


        //Collections.shuffle(Arrays.asList(adjacentTiles));

        Neighborship[] neighborships = current.neighborships;
        Collections.shuffle(Arrays.asList(neighborships), random);

        for (int i = 0; i < neighborships.length; i++) {
            //Cell adjacent = grid.getNeighbor(current, i);
            Neighborship neigh = neighborships[i];
            if (neigh == null) continue;

            //int nx = cx + dir.dx;
            //int ny = cy + dir.dy;
            int nx = neigh.target.x;
            int ny = neigh.target.y;

            if ((grid.cells[nx][ny].walls == 0)) {

                grid.connect(neigh);

                //maze[cx][cy] |= dir.bit;
                //maze[nx][ny] |= dir.opposite.bit;
                explore(neigh.target);
            }
        }
    }

    private static boolean between(int v, int upper) {
        return (v >= 0) && (v < upper);
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

    public static void main(String[] args) {
        int x = args.length >= 1 ? (Integer.parseInt(args[0])) : 8;
        int y = args.length == 2 ? (Integer.parseInt(args[1])) : 8;
        RecursiveDFS maze = new RecursiveDFS(new SquareGrid(10, 10));
        maze.display();
    }
}
