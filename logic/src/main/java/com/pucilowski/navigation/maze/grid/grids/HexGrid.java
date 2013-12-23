package com.pucilowski.navigation.maze.grid.grids;

import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.paint.PointD;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public class HexGrid extends Grid {

    public static final int SIDES = 6;

    public static final Point[] offsets = {
            new Point(0, -1), // n
            new Point(1, -1), // ne
            new Point(1, 0), // se

            new Point(0, 1), // s
            new Point(-1, 0), // sw
            new Point(-1, -1), // nw
    };

    public HexGrid(int width, int height) {
        super(width, height, SIDES);
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        //return offsets[index];
        int x = offsets[index].x;
        int y = offsets[index].y;
        if (cell.x % 2 == 1 && x!=0) y++;

        return new Point(x, y);
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

    public static final PointD[] points = {
            new PointD(X_LEFT, Y_TOP),
            new PointD(X_RIGHT, Y_TOP),
            new PointD(X_FAR_RIGHT, Y_MIDDLE),
            new PointD(X_RIGHT, Y_BOTTOM),
            new PointD(X_LEFT, Y_BOTTOM),
            new PointD(X_FAR_LEFT, Y_MIDDLE),
    };
    @Override
    public PointD getSize() {
        return new PointD(width*1.5,height*3);
    }

    @Override
    public Point getLocation(Cell cell, int size) {
        double px = size * TRIANGLE_WIDTH + cell.x * (int) (size * TRIANGLE_WIDTH) + cell.x * (int) (size * SIDE_LENGTH);
        double py = 2 * (int) (TRIANGLE_HEIGHT * size) * cell.y;

        if (cell.x % 2 == 1) py += (TRIANGLE_HEIGHT * size);

        int x = (int) (px );
        int y = (int) (py );

        return new Point(x, y);
    }

    @Override
    public Point getPoint(Cell cell, int index, int size) {
        PointD dp = points[index];

        int x = (int) (dp.x * size);
        int y = (int) (dp.y * size);

        return new Point(x, y);
    }


}
