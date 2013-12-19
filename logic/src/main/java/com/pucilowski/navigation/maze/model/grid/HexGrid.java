package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by martin on 10/12/13.
 */
public class HexGrid extends Grid {


    public HexGrid(int width, int height) {
        super(width, height,6);
    }

    @Override
    public Cell getAdjacentTile(Cell current, int index) {
        return null;
    }

    @Override
    public Cell[] getAdjacentTiles(Cell cell) {
        ArrayList<Cell> adjacent = new ArrayList<Cell>();

        Point[] offsets = {
                new Point(0, -1),
                new Point(1, 0),
                new Point(0, 1),
                new Point(-1, 0),
        };

        for (int i = 0; i < offsets.length; i++) {
            Point p = offsets[i];

            int x = cell.x + p.x;
            int y = cell.y + p.y;

            if (x < 0 || y < 0 || x >= width || y >= height) continue;

            adjacent.add( cells[cell.x + p.x][cell.y + p.y]);
        }

        return adjacent.toArray(new Cell[adjacent.size()]);    }

    @Override
    public void display() {

    }
}
