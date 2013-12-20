package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public class SquareGrid extends Grid {

    public static final Point[] offsets = {
            new Point(0, -1), // n
            new Point(1, 0), // e
            new Point(0, 1), // s
            new Point(-1, 0), // w
    };

    public SquareGrid(int width, int height) {
        super(width, height, 4);
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        return offsets[index];
    }

    @Override
    public Point getLocation(Cell cell, int size) {
        int px = size * cell.x;
        int py = size * cell.y;

        return new Point(px, py);
    }

    @Override
    public Point getPoint(Cell cell, int index, int size) {
        return null;
    }

    @Override
    public Polygon getSide(Cell cell, int index, int size) {
        Point p = getOffset(cell, index);

        // (0,0),(1,0)
        // (1,0),(1,1)
        // (1,1),(0,1)
        // (0,1),(0,0)

        //

        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 1),
                new Point(0, 1)
        };


        Point a = points[index];
        Point b = points[(index + 1) % sides];

        return new Polygon(
                new int[]{a.x*size, b.x*size},
                new int[]{a.y*size, b.y*size},
                2
        );

    }

    @Override
    public Polygon getPolygon(Cell cell, int index) {
        return null;
    }
}
