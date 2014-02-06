package com.pucilowski.navigation.logic.grid.grids;

import com.pucilowski.navigation.logic.grid.Cell;
import com.pucilowski.navigation.logic.grid.Grid;
import com.pucilowski.navigation.logic.grid.misc.PointD;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public class UpsilonGrid extends Grid {

    public static final int SIDES_WHITE = 8;
    public static final int SIDES_BLACK = 4;

    public static final Point[] OFFSETS_WHITE = {
            new Point(0, -1), // n
            new Point(1, -1), // ne

            new Point(1, 0), // e
            new Point(1, 1), // se

            new Point(0, 1), // s
            new Point(-1, 1), // sw

            new Point(-1, 0), // w
            new Point(-1, -1), // nw
    };

    public static final Point[] OFFSETS_BLACK = {
            new Point(0, -1), // n
            new Point(1, 0), // e
            new Point(0, 1), // s
            new Point(-1, 0), // w
    };


    public UpsilonGrid(int width, int height) {
        super(width, height);
    }

    private boolean isWhite(Cell cell) {
        return cell.x % 2 == cell.y % 2;
    }

    @Override
    public int getSides(Cell cell) {
        return isWhite(cell) ? 8 : 4;
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        //return offsets[side];
        Point[] offsets = isWhite(cell) ? OFFSETS_WHITE : OFFSETS_BLACK;

        int x = offsets[index].x;
        int y = offsets[index].y;

        return new Point(x, y);
    }

    @Override
    public int getOppositeIndex(Cell cell, int index) {
        //int index2 = (index + (cell.sides / 2)) % cell.sides;

        int sides = 8;
        if (isWhite(cell)) {
            // 0 -> 0
            // 2 -> 1
            // 4 -> 2
            // 6 -> 3
            if (index % 2 == 0) {
                index /= 2;
                sides = 4;
            }
        } else {
            index *= 2;
            sides = 8;
        }


        //return (index + (SIDES_BLACK/ 2)) % SIDES_BLACK;

        //System.out.println("Before: " + side + " After: " + index2);

        int index2 = (index + (sides / 2)) % sides;
        return index2;
        //return cell.x % 2 == cell.y % 2 ? identicalOffsets[side] : distinctOffsets[side];
    }

    // render

/*
    //public static final double TRIANGLE_HEIGHT = Math.sin(Math.toRadians(60));
    //double TRIANGLE_HEIGHT = 1D;





*/

    public static final double SIDE_LENGTH = 1.0D;
    public static final double CORNER_WIDTH = 0.7D;

    public int sideLength;
    public int cornerWidth;
    public int combined;

  /*  public static final PointD[] POINTS_WHITE = {
            new PointD(0, -1), // NW
            new PointD(1, -1), // NE
            new PointD(COMBINED, 0),
            new PointD(1 + CORNER_WIDTH, 1),
            new PointD(1, 2),
            new PointD(0, 2),
            new PointD(-1, 1),
            new PointD(-1, 0),
    };

    public static final PointD[] POINTS_BLACK = {
            new PointD(0, 0),
            new PointD(1, 0),
            new PointD(1, 1),
            new PointD(0, 1),
    };*/

    public static Point[] pointsWhite = new Point[8];
    public static Point[] pointsBlack = new Point[4];


    @Override
    public void resize(int size) {
        sideLength = (int) (SIDE_LENGTH * size);
        cornerWidth = (int) (CORNER_WIDTH * size);
        combined = sideLength + cornerWidth;

        pointsWhite = new Point[]{
                new Point(0, -cornerWidth), // NW
                new Point(sideLength, -cornerWidth), // NE
                new Point(combined, 0),
                new Point(combined, sideLength),
                new Point(sideLength, combined),
                new Point(0, combined),
                new Point(-cornerWidth, sideLength),
                new Point(-cornerWidth, 0),
        };

        pointsBlack = new Point[]{
                new Point(0, 0),
                new Point(sideLength, 0),
                new Point(sideLength, sideLength),
                new Point(0, sideLength),
        };
    }

    @Override
    public PointD getScaledSize() {
        double w = CORNER_WIDTH + width * (SIDE_LENGTH + CORNER_WIDTH);
        double h = CORNER_WIDTH + height * (SIDE_LENGTH + CORNER_WIDTH);

        return new PointD(w, h);
    }

    @Override
    public Point getSize() {
        int w = cornerWidth + width * (sideLength + cornerWidth);
        int h = cornerWidth + height * (sideLength + cornerWidth);
        return new Point(w, h);
    }


    @Override
    public Point getLocation(Cell cell) {
        int x = cornerWidth + cell.x * (sideLength + cornerWidth);
        int y = cornerWidth + cell.y * (sideLength + cornerWidth);

        return new Point(x, y);
    }


    @Override
    public Point getPoint(Cell cell, int index) {
        Point[] points = isWhite(cell) ? pointsWhite : pointsBlack;

        return points[index];
    }


}
