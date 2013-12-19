package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by martin on 10/12/13.
 */
public class HexGrid extends Grid {

    public static final Point[] offsets = {
            new Point(1, 0), // ne
            new Point(1, 1), // se

            new Point(0, 1), // s

            new Point(-1, 1), // sw
            new Point(-1, 0), // nw

            new Point(0, -1), // n
    };

    public HexGrid(int width, int height) {
        super(width, height, 6);
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        //return offsets[index];
        int x = offsets[index].x;
        int y = offsets[index].y;
        if (cell.x % 2 == 1) y++;

        return new Point(x, y);
    }


    @Override
    public void display() {

    }
}
