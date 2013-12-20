package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.grid.paint.*;
import com.pucilowski.navigation.maze.model.grid.paint.Point;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public class HexGrid extends Grid {

    public static final java.awt.Point[] offsets = {
            new java.awt.Point(1, -1), // ne
            new java.awt.Point(1, 0), // se

            new java.awt.Point(0, 1), // s

            new java.awt.Point(-1, 0), // sw
            new java.awt.Point(-1, -1), // nw

            new java.awt.Point(0, -1), // n
    };


    public HexGrid(int width, int height) {
        super(width, height, 6);

    }

    @Override
    public java.awt.Point getOffset(Cell cell, int index) {
        //return offsets[index];
        int x = offsets[index].x;
        int y = offsets[index].y;
        if (cell.x % 2 == 1) y++;

        return new java.awt.Point(x, y);
    }

    // paint

/*    public static final double TRIANGLE_WIDTH = 0.25D;
    public static final double TRIANGLE_HEIGHT = 0.875D / 2D;
    public static final double SIDE_LENGTH = 0.5D;
    //public static final double TRIANGLE_HEIGHT = Math.sin(Math.toRadians(60));
    //double TRIANGLE_HEIGHT = 1D;

    public static final double X_LEFT = 0.25D;
    public static final double X_RIGHT = 0.75D;

    public static final double X_FAR_LEFT = 0D;
    public static final double X_FAR_RIGHT = 1D;

    public static final double Y_TOP = 0;
    public static final double Y_MIDDLE = TRIANGLE_HEIGHT;
    public static final double Y_BOTTOM = 2 * TRIANGLE_HEIGHT;*/

    public static final double SIDE_LENGTH = 1.0D;
    public static final double TRIANGLE_WIDTH = 0.5D;
    public static final double TRIANGLE_HEIGHT = 0.875D;
    //public static final double TRIANGLE_HEIGHT = Math.sin(Math.toRadians(60));

    public static final double X_LEFT = 0D;
    public static final double X_RIGHT = 1D;

    public static final double X_FAR_LEFT = -0.5D;
    public static final double X_FAR_RIGHT = 1.5D;

    public static final double Y_TOP = 0;
    public static final double Y_MIDDLE = TRIANGLE_HEIGHT;
    public static final double Y_BOTTOM = 2 * TRIANGLE_HEIGHT;

    public static final Point[] points = {
            new Point(X_RIGHT, Y_TOP),
            new Point(X_FAR_RIGHT, Y_MIDDLE),
            new Point(X_RIGHT, Y_BOTTOM),
            new Point(X_LEFT, Y_BOTTOM),
            new Point(X_FAR_LEFT, Y_MIDDLE),
            new Point(X_LEFT, Y_TOP),
    };


    @Override
    public java.awt.Point getLocation(Cell cell, int size) {
        double px = TRIANGLE_WIDTH + cell.x * (TRIANGLE_WIDTH + SIDE_LENGTH);
        double py = 2 * TRIANGLE_HEIGHT * cell.y;

        if (cell.x % 2 == 1) py += TRIANGLE_HEIGHT;

        int x = (int) (px * size);
        int y = (int) (py * size);

        return new java.awt.Point(x, y);
    }

    @Override
    public java.awt.Point getPoint(Cell cell, int index, int size) {
        Point dp = points[index];

        int x = (int) (dp.x * size);
        int y = (int) (dp.y * size);

        return new java.awt.Point(x, y);
    }

    @Override
    public Polygon getSide(Cell cell, int index, int size) {
        java.awt.Point a = getPoint(cell, index, size);
        java.awt.Point b = getPoint(cell, (index + 1) % sides, size);

        return new Polygon(
                new int[]{a.x, b.x},
                new int[]{a.y, b.y},
                2
        );
    }

    @Override
    public Polygon getPolygon(Cell cell, int size) {
        int[] xs = new int[sides];
        int[] ys = new int[sides];
        for (int i = 0; i < sides; i++) {
            java.awt.Point p = getPoint(cell, i, size);

            xs[i] = p.x;
            ys[i] = p.y;
        }

        return new Polygon(xs, ys, sides);
    }


}
