package com.pucilowski.navigation.mazes.grid.grids;

import com.pucilowski.navigation.mazes.grid.Cell;
import com.pucilowski.navigation.mazes.grid.Grid;
import com.pucilowski.navigation.mazes.grid.misc.PointD;

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
        super(width, height);
    }

    @Override
    public int getSides(Cell cell) {
        return SIDES;
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        //return offsets[side];
        int x = offsets[index].x;
        int y = offsets[index].y;
        if (cell.x % 2 == 1 && x != 0) y++;

        return new Point(x, y);
    }


    // render

/*
    //public static final double TRIANGLE_HEIGHT = Math.sin(Math.toRadians(60));
    //double TRIANGLE_HEIGHT = 1D;





*/

    public static final double SIDE_LENGTH = 1.0D;
    public static final double TRIANGLE_WIDTH = 0.5D;
    public static final double TRIANGLE_HEIGHT = 0.875D;
    //public static final double TRIANGLE_HEIGHT = Math.sin(Math.toRadians(60));
    //public static final double SIDE_LENGTH = 0.5D;
    //public static final double TRIANGLE_WIDTH = 0.25D;
    //public static final double TRIANGLE_HEIGHT = 0.875D / 2D;


    //public static final double X_LEFT = 0D;
    //public static final double X_RIGHT = 1D;
    public static final double X_LEFT = 0.25D;
    public static final double X_RIGHT = 0.75D;

    //public static final double X_FAR_LEFT = -0.5D;
    //public static final double X_FAR_RIGHT = 1.5D;
    public static final double X_FAR_LEFT = 0D;
    public static final double X_FAR_RIGHT = 1D;

    public static final double Y_TOP = 0;
    public static final double Y_MIDDLE = TRIANGLE_HEIGHT;
    public static final double Y_BOTTOM = TRIANGLE_HEIGHT * 2;

    public int sideLength;
    public int triangleWidth;
    public int triangleHeight;

    public static final PointD[] POINTS = {
            new PointD(X_LEFT, Y_TOP),
            new PointD(X_RIGHT, Y_TOP),
            new PointD(X_FAR_RIGHT, Y_MIDDLE),
            new PointD(X_RIGHT, Y_BOTTOM),
            new PointD(X_LEFT, Y_BOTTOM),
            new PointD(X_FAR_LEFT, Y_MIDDLE),
    };

    public static Point[] points;



    @Override
    public void resize(int size) {

        sideLength = (int) (SIDE_LENGTH * size);
        triangleWidth = (int) (TRIANGLE_WIDTH * size);
        triangleHeight = (int) (TRIANGLE_HEIGHT * size);

        points = new Point[]{
                new Point(0, 0),
                new Point(sideLength, 0),
                new Point(sideLength + triangleWidth, triangleHeight),
                new Point(sideLength, triangleHeight * 2),
                new Point(0, triangleHeight * 2),
                new Point(-triangleWidth, triangleHeight),
        };
    }

    @Override
    public PointD getScaledSize() {
        double w = TRIANGLE_WIDTH + width * (SIDE_LENGTH + TRIANGLE_WIDTH);
        double h = TRIANGLE_HEIGHT + height * TRIANGLE_HEIGHT * 2D;// * TRIANGLE_HEIGHT * 3D;

        return new PointD(w, h);
    }

    @Override
    public Point getSize() {
        int w = triangleWidth + width * (sideLength + triangleWidth);
        int h = triangleHeight + height * triangleHeight * 2;
        return new Point(w,h);
    }

    @Override
    public Point getLocation(Cell cell) {
        int x = triangleWidth + cell.x * triangleWidth + cell.x * sideLength;
        int y = 2 * triangleHeight * cell.y;

        if (cell.x % 2 == 1) y += triangleHeight;


        return new Point(x, y);
    }


    @Override
    public Point getPoint(Cell cell, int index) {
        return points[index];
    }


}
