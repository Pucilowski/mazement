package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public class TriGrid extends Grid {

    public static final java.awt.Point[] identical = {
            new java.awt.Point(1, 0), // e
            new java.awt.Point(0, 1), // s
            new java.awt.Point(-1, 0), // w
    };

    public static final java.awt.Point[] distinct = {
/*            new Point(0, -1), // n
            new Point(1, 0), // e
            new Point(-1, 0), // w*/

            new java.awt.Point(-1, 0), // w
            new java.awt.Point(0, -1), // n
            new java.awt.Point(1, 0), // e

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
    public java.awt.Point getOffset(Cell cell, int index) {
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
    public java.awt.Point getLocation(Cell cell, int size) {
        int px = size / 2 * cell.x;
        int py = size * cell.y;

        return new java.awt.Point(px, py);
    }

    @Override
    public java.awt.Point getPoint(Cell cell, int index, int size) {
        return null;
    }

    @Override
    public Polygon getSide(Cell cell, int index, int size) {
        java.awt.Point p = getOffset(cell, index);

        size /= 2;

        // (0,0),(1,0)
        // (1,0),(1,1)
        // (1,1),(0,1)
        // (0,1),(0,0)

        //

        java.awt.Point[] identicalPoints = {
/*                new Point(0, 0),
                new Point(1, 1),
                new Point(0, 1),*/
                new java.awt.Point(1, 0),
                new java.awt.Point(2, 2),
                new java.awt.Point(0, 2),
        };


        java.awt.Point[] distinctPoints = {
                new java.awt.Point(1, 2),
                new java.awt.Point(0, 0),
                new java.awt.Point(2, 0),
        };

        java.awt.Point[] points = cell.x % 2 == cell.y % 2 ? identicalPoints : distinctPoints;


        java.awt.Point a = points[index];
        java.awt.Point b = points[(index + 1) % sides];

        return new Polygon(
                new int[]{a.x * size, b.x * size},
                new int[]{a.y * size, b.y * size},
                2
        );

    }

    @Override
    public Polygon getPolygon(Cell cell, int size) {
        com.pucilowski.navigation.maze.model.grid.paint.Point[] identicalPoints = {
                new com.pucilowski.navigation.maze.model.grid.paint.Point(1, 0),
                new com.pucilowski.navigation.maze.model.grid.paint.Point(2, 2),
                new com.pucilowski.navigation.maze.model.grid.paint.Point(0, 2),
        };

        com.pucilowski.navigation.maze.model.grid.paint.Point[] distinctPoints = {
                new com.pucilowski.navigation.maze.model.grid.paint.Point(0, 0),
                new com.pucilowski.navigation.maze.model.grid.paint.Point(2, 0),
                new com.pucilowski.navigation.maze.model.grid.paint.Point(1, 2),
        };

        com.pucilowski.navigation.maze.model.grid.paint.Point[] points = cell.x % 2 == cell.y % 2 ? identicalPoints : distinctPoints;

        int[] xs = new int[sides];
        int[] ys = new int[sides];
        for (int i = 0; i < sides; i++) {
            xs[i] = (int) (points[i].x * size);
            ys[i] = (int) (points[i].y * size);
        }

        return new Polygon(xs, ys, 3);
    }

}
