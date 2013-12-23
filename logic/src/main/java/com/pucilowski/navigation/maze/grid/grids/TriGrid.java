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
    public static final PointD[] identicalPoints = {
            new PointD(0.5, 0),
            new PointD(1, 1),
            new PointD(0, 1),
    };


    public static final PointD[] distinctPoints = {
            new PointD(0.5, 1),
            new PointD(0, 0),
            new PointD(1, 0),
    };

    @Override
    public PointD getSize() {
        return new PointD((double)width/4D,(double)height);
    }

    @Override
    public Point getLocation(Cell cell, int size) {
        int px = size / 2 * cell.x;
        int py = size * cell.y;

        return new Point(px, py);
    }

    @Override
    public Point getPoint(Cell cell, int index, int size) {
        PointD[] points = cell.x % 2 == cell.y % 2 ? identicalPoints : distinctPoints;

        int x = (int) (size * points[index].x);
        int y = (int) (size * points[index].y);

        return new Point(x, y);
    }

/*    @Override
    public Polygon getSide(Cell cell, int index, int size) {
        Point a = getPoint(cell, index, size);
        Point b = getPoint(cell, (index + 1) % sides, size);

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
            Point point = getPoint(cell, i, size);
            xs[i] = point.x;
            ys[i] = point.y;
        }

        return new Polygon(xs, ys, 3);
    }*/

}
