package com.pucilowski.navigation.maze.grid;

import com.pucilowski.navigation.maze.grid.paint.PointD;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by martin on 10/12/13.
 */
public abstract class Grid {

    public final int width;
    public final int height;
    public final int sides;
    public Cell[][] cells;

    public Grid(int width, int height, int sides) {
        this.width = width;
        this.height = height;
        this.sides = sides;
        cells = new Cell[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = new Cell(x, y, sides);
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = cells[x][y];

                for (int side = 0; side < sides; side++) {
                    Cell neighbor = getNeighbor(cell, side);
                    if (neighbor == null) continue;

                    //cell.edges[(side+2)%sides] = new Edge(cell, neighbor, side);
                    cell.edges[side] = new Edge(cell, neighbor, side);
                }
            }
        }

    }

    public boolean contains(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public void connect(Edge n) {

        int source = (int) Math.pow(2, n.index);
        int opp = getOppositeIndex(n.source, n.index);
        int target = (int) Math.pow(2, opp);

        //System.out.println("S: " + n.source + " T: " + n.target);

        n.source.walls |= source;
        n.target.walls |= target;
    }


    public void disconnect(Edge n) {

        int source = (int) Math.pow(2, n.index);
        int opp = getOppositeIndex(n.source, n.index);
        int target = (int) Math.pow(2, opp);

        //System.out.println("S: " + n.source + " T: " + n.target);

        n.source.walls -= source;
        n.target.walls -= target;
    }

    public void toIsland(Cell cell) {

        for (Edge edge : cell.getEdges()) {
            disconnect(edge);
        }

    }

    public boolean isConnected(Edge n) {
        int source = (int) Math.pow(2, n.index);
        int opp = getOppositeIndex(n.source, n.index);
        int target = (int) Math.pow(2, opp);

        return (n.source.walls & source) == source && (n.target.walls & target) == target;

    }


    public abstract Point getOffset(Cell cell, int index);

    public int getOppositeIndex(Cell cell, int index) {


        int index2 = (index + (sides / 2)) % sides;

        //System.out.println("Before: " + index + " After: " + index2);


        return index2;
        //return cell.x % 2 == cell.y % 2 ? identicalOffsets[index] : distinctOffsets[index];
    }

    public final Cell getNeighbor(Cell cell, int index) {
        Point o = getOffset(cell, index);

        int x = cell.x + o.x;
        int y = cell.y + o.y;
        if (!contains(x, y)) return null;

        return cells[x][y];
    }

    public final Cell[] getNeighbors(Cell cell) {
        ArrayList<Cell> adjacent = new ArrayList<Cell>();

        for (int i = 0; i < sides; i++) {
            Cell n = getNeighbor(cell, i);
            if (n == null) continue;
            adjacent.add(n);
        }

        return adjacent.toArray(new Cell[adjacent.size()]);
    }

    // paint

    public abstract PointD getSize();

    public abstract Point getLocation(Cell cell, int size);

    public abstract Point getPoint(Cell cell, int index, int size);

/*    public abstract Polygon getSide(Cell cell, int index, int size);

    public abstract Polygon getPolygon(Cell cell, int size);*/

    public final Polygon getSide(Cell cell, int index, int size) {
        java.awt.Point a = getPoint(cell, index, size);
        java.awt.Point b = getPoint(cell, (index + 1) % sides, size);

        return new Polygon(
                new int[]{a.x, b.x},
                new int[]{a.y, b.y},
                2
        );
    }

    public final Polygon getPolygon(Cell cell, int size) {
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
