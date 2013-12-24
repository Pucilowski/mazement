package com.pucilowski.navigation.maze.grid.grids;

import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.paint.PointD;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public class TriGrid extends Grid {

    public static final Point[] identicalOffsets = {
            new Point(1, 0), // e
            new Point(0, 1), // s
            new Point(-1, 0), // w
    };

    public static final Point[] distinctOffsets = {
            new Point(-1, 0), // w
            new Point(0, -1), // n
            new Point(1, 0), // e

    };

    public TriGrid(int width, int height) {
        super(width, height, 3);
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        //return cell.x % 2 == cell.y % 2 ? identicalOffsets[index] : distinctOffsets[index];

/*        boolean identicalOffsets = cell.x % 2 == cell.y % 2;

        int i = index*2;
        if(!identicalOffsets) i+=1;

        return offsets[i];*/

        return cell.x % 2 == cell.y % 2 ? identicalOffsets[index] : distinctOffsets[index];
    }

    @Override
    public int getOppositeIndex(Cell cell, int index) {

        return index;

        //return cell.x % 2 == cell.y % 2 ? identicalOffsets[index] : distinctOffsets[index];
    }




    // paint
    public static final PointD[] IDENTICAL_POINTS = {
            new PointD(0.5, 0),
            new PointD(1, 1),
            new PointD(0, 1),
    };


    public static final PointD[] DISTINCT_POINTS = {
            new PointD(0.5, 1),
            new PointD(0, 0),
            new PointD(1, 0),
    };

    public Point[] identicalPoints;
    public Point[] distinctPoints;

    public static final double TRIANGLE_HEIGHT = Math.sin(Math.toRadians(60));
    public static final double SIDE_LENGTH = 1D;
    public static final double TRIANGLE_WIDTH = 0.5D;
    //public static final double TRIANGLE_HEIGHT = 0.875D;

    public int sideLength;
    public int triangleWidth;
    public int triangleHeight;

    @Override
    public void resize(int size) {
        sideLength = (int) (SIDE_LENGTH * size);
        triangleWidth = (int) (TRIANGLE_WIDTH * size);
        triangleHeight = (int) (TRIANGLE_HEIGHT * size);

        identicalPoints = new Point[]{
                new Point(triangleWidth, 0),
                new Point(sideLength, triangleHeight),
                new Point(0, triangleHeight),
        };


        distinctPoints = new Point[]{
                new Point(triangleWidth, triangleHeight),
                new Point(0, 0),
                new Point(sideLength, 0),
        };
    }

    @Override
    public PointD getScaledSize() {
        return new PointD((double) width / 2D + 0.5D, (height * TRIANGLE_HEIGHT));
    }

    @Override
    public Point getSize() {
        return new Point(triangleWidth * width + triangleWidth, height * triangleHeight);
    }


    @Override
    public Point getLocation(Cell cell) {
        int px = triangleWidth * cell.x;
        int py = triangleHeight * cell.y;

        return new Point(px, py);
    }


    @Override
    public Point getPoint(Cell cell, int index) {
        Point[] points = cell.x % 2 == cell.y % 2 ? identicalPoints : distinctPoints;

        return points[index];
    }

}
