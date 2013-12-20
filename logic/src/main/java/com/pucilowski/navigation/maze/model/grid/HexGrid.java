package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.grid.paint.DoublePoint;
import com.pucilowski.navigation.maze.model.grid.paint.GridPaint;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public class HexGrid extends Grid {

    public static final Point[] offsets = {
            new Point(1, -1), // ne
            new Point(1, 0), // se

            new Point(0, 1), // s

            new Point(-1, 0), // sw
            new Point(-1, -1), // nw

            new Point(0, -1), // n
    };

    GridPaint paint;

    public HexGrid(int width, int height) {
        super(width, height, 6);

        paint = new GridPaint(width, height);
    }

    @Override
    public Point getOffset(Cell cell, int index) {
        //return offsets[index];
        int x = offsets[index].x;
        int y = offsets[index].y;
        if (cell.x % 2 == 1) y++;

        return new Point(x, y);
    }

    // paint

    double triangleWidth = 0.5D;
    double triangleHeight = 0.75D;
    //double triangleHeight = Math.sin(Math.toRadians(60));
    //double triangleHeight = 1D;

    double leftX = 0D;
    double rightX = 1D;

    double farLeftX = -0.5D;
    double farRightX = 1.5D;

    double topY = 0;
    double midY = triangleHeight;
    double bottomY = 2 * triangleHeight;

    DoublePoint[] points = {
            new DoublePoint(rightX, topY),
            new DoublePoint(farRightX, midY),
            new DoublePoint(rightX, bottomY),
            new DoublePoint(leftX, bottomY),
            new DoublePoint(farLeftX, midY),
            new DoublePoint(leftX, topY),
    };


    @Override
    public Point getLocation(Cell cell, int size) {
        //size/=2;

        double px = triangleWidth + cell.x + cell.x * triangleWidth;
        double py = 2 * triangleHeight * cell.y;

        if (cell.x % 2 == 1) py += triangleHeight;

        int x = (int) (px * size);
        int y = (int) (py * size);

        return new Point(x, y);
    }

    @Override
    public Point getPoint(Cell cell, int index, int size) {
        //size/=2;
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
        //size/=2;
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
