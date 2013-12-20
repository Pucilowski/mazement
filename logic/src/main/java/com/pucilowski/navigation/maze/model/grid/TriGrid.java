package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.grid.paint.DoublePoint;

import java.awt.*;
import java.awt.geom.Point2D;

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
/*            new Point(0, -1), // n
            new Point(1, 0), // e
            new Point(-1, 0), // w*/

            new Point(-1, 0), // w
            new Point(0, -1), // n
            new Point(1, 0), // e

    };


/*    public static final Point[] offsets = {
            new Point(1, 0), // e
            new Point(1, 0), // e

            new Point(0, 1), // s

            new Point(-1, 0), // w
            new Point(-1, 0), // w

            new Point(0, -1), // n
    };*/


    public TriGrid(int width, int height) {
        super(width, height, 3);
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        //return cell.x % 2 == cell.y % 2 ? identical[index] : distinct[index];

/*        boolean identical = cell.x % 2 == cell.y % 2;

        int i = index*2;
        if(!identical) i+=1;

        return offsets[i];*/

        return cell.x % 2 == cell.y % 2 ? identical[index] : distinct[index];
    }

    @Override
    public int getOppositeIndex(Cell cell, int index) {

        return index;

        //return cell.x % 2 == cell.y % 2 ? identical[index] : distinct[index];
    }

    @Override
    public Point getLocation(Cell cell, int size) {
        int px = size / 2 * cell.x;
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

        size /= 2;

        // (0,0),(1,0)
        // (1,0),(1,1)
        // (1,1),(0,1)
        // (0,1),(0,0)

        //

        Point[] identicalPoints = {
/*                new Point(0, 0),
                new Point(1, 1),
                new Point(0, 1),*/
                new Point(1, 0),
                new Point(2, 2),
                new Point(0, 2),
        };


        Point[] distinctPoints = {
                new Point(1, 2),
                new Point(0, 0),
                new Point(2, 0),
        };

        Point[] points = cell.x % 2 == cell.y % 2 ? identicalPoints : distinctPoints;


        Point a = points[index];
        Point b = points[(index + 1) % sides];

        return new Polygon(
                new int[]{a.x * size, b.x * size},
                new int[]{a.y * size, b.y * size},
                2
        );

    }

    @Override
    public Polygon getPolygon(Cell cell, int size) {
        DoublePoint[] identicalPoints = {
                new DoublePoint(1, 0),
                new DoublePoint(2, 2),
                new DoublePoint(0, 2),
        };

        DoublePoint[] distinctPoints = {
                new DoublePoint(0, 0),
                new DoublePoint(2, 0),
                new DoublePoint(1, 2),
        };

        DoublePoint[] points = cell.x % 2 == cell.y % 2 ? identicalPoints : distinctPoints;

        int[] xs = new int[sides];
        int[] ys = new int[sides];
        for (int i = 0; i < sides; i++) {
            xs[i] = (int) (points[i].x * size);
            ys[i] = (int) (points[i].y * size);
        }

        return new Polygon(xs, ys, 3);
    }

}
