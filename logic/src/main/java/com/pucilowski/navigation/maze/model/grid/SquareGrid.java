package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by martin on 10/12/13.
 */
public class SquareGrid extends Grid {

    public static final Point[] offsets = {
            new Point(0, -1),
            new Point(1, 0),
            new Point(0, 1),
            new Point(-1, 0),
    };

    public SquareGrid(int width, int height) {
        super(width, height, 4);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public Cell getAdjacentTile(Cell cell, int index) {

        int x = cell.x + offsets[index].x;
        int y = cell.y + offsets[index].y;

        if (!contains(x, y)) return null;

        return cells[x][y];
    }

    @Override
    public Cell[] getAdjacentTiles(Cell cell) {
        ArrayList<Cell> adjacent = new ArrayList<Cell>();


        for (int i = 0; i < sides; i++) {

            Cell n = getAdjacentTile(cell, i);
            if (n == null) continue;
            adjacent.add(n);
        }

        return adjacent.toArray(new Cell[adjacent.size()]);
    }

    /**
     * sourced from rosettacode
     */
    public void display() {

        int x = width;
        int y = height;


        for (int i = 0; i < y; i++) {
            // draw the north edge
            for (int j = 0; j < x; j++) {
                System.out.print((cells[j][i].walls & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < x; j++) {
                System.out.print((cells[j][i].walls & 8) == 0 ? "|   " : "    ");
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
