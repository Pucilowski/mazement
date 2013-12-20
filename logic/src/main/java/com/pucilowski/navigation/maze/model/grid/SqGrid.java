package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.grid.paint.DoublePoint;

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
        super(width, height, 4);
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        return offsets[index];
    }

    // paint

    public static final DoublePoint[] points = {
            new DoublePoint(0, 0),
            new DoublePoint(1, 0),
            new DoublePoint(1, 1),
            new DoublePoint(0, 1)
    };

    @Override
    public Point getLocation(Cell cell, int size) {
        int px = size * cell.x;
        int py = size * cell.y;

        return new Point(px, py);
    }

    @Override
    public Point getPoint(Cell cell, int index, int size) {
        DoublePoint dp = points[index];

        int x = (int) (dp.x * size);
        int y = (int) (dp.y * size);

        return new Point(x, y);
    }

    @Override
    public Polygon getSide(Cell cell, int index, int size) {
        //size/=2;
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
            Point p = getPoint(cell, i, size);

            xs[i] = p.x;
            ys[i] = p.y;
        }

        return new Polygon(xs, ys, sides);
    }
}
