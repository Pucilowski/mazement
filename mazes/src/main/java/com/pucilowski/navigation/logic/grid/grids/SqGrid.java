package com.pucilowski.navigation.logic.grid.grids;

import com.pucilowski.navigation.logic.grid.Cell;
import com.pucilowski.navigation.logic.grid.Grid;
import com.pucilowski.navigation.logic.grid.misc.PointD;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public class SqGrid extends Grid {

    public static final Point[] offsets = {
            new Point(0, -1), // n
            new Point(1, 0), // e
            new Point(0, 1), // s
            new Point(-1, 0), // w
    };

    public SqGrid(int width, int height) {
        super(width, height);
    }

    @Override
    public int getSides(Cell cell) {
        return 4;
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        return offsets[index];
    }


    // paint

    public static final PointD[] POINTS = {
            new PointD(0, 0),
            new PointD(1, 0),
            new PointD(1, 1),
            new PointD(0, 1),
    };


    public Point[] points = {
            new Point(0, 0),
            new Point(1, 0),
            new Point(1, 1),
            new Point(0, 1),
    };

    int sideLength;

    @Override
    public void resize(int size) {
        sideLength = size;

        points = new Point[]{
                new Point(0, 0),
                new Point(sideLength, 0),
                new Point(sideLength, sideLength),
                new Point(0, sideLength),
        };
    }

    @Override
    public PointD getScaledSize() {
        return new PointD(width, height);
    }

    @Override
    public Point getSize() {
        return new Point(sideLength * width, sideLength * height);
    }

    @Override
    public Point getLocation(Cell cell) {
        int px = sideLength * cell.x;
        int py = sideLength * cell.y;

        return new Point(px, py);
    }

    @Override
    public Point getPoint(Cell cell, int index) {
        return points[index];
    }
}
