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

    @Override
    public Point getOffset(Cell cell, int index) {
        return offsets[index];
    }
}
