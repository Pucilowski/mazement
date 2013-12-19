package com.pucilowski.navigation.maze.generation;

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

        visited.add(grid.cells[0][0]);


        while (step()) ;

    }

    public boolean step() {

        //DIR[] dirs = DIR.values();
        //Collections.shuffle(Arrays.asList(dirs));
        //for (DIR dir : dirs) {

        //System.out.println("cx: " + cx + " cy: " + cy);

        if(visited.empty()) return false;

        Cell current = visited.peek();

        System.out.println("step: " + current);

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

                return true;
                //maze[cx][cy] |= dir.bit;
                //maze[nx][ny] |= dir.opposite.bit;
            }

        }

        System.out.println("nothing to do : " + current );

        visited.pop();

        return true;

        //Collections.shuffle(Arrays.asList(adjacentTiles));

        //for (int i = 0; i < adjacentTiles.length; i++) {

        //Cell[] adjacentTiles = grid.getAdjacentTiles(current);


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
        StackDFS maze = new StackDFS(new SquareGrid(20, 15));
        maze.display();
    }
}
