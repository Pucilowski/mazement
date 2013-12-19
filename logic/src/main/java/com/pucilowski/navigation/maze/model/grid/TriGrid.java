package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public class TriGrid extends Grid {

    public static final Point[] identical = {
            new Point(1, 0), // e
            new Point(0, 1), // s
            new Point(-1, 0), // w
    };

    public static final Point[] distinct = {
            new Point(1, 0), // e
            new Point(-1, 0), // w
            new Point(0, -1), // n
    };

    public TriGrid(int width, int height) {
        super(width, height, 3);
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        return cell.x % 2 == cell.y % 2 ? identical[index] : distinct[index];
    }


}
