package com.pucilowski.navigation.logic.grid;

import com.pucilowski.navigation.logic.grid.misc.PointD;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by martin on 10/12/13.
 */
public abstract class Grid {

    public final int width;
    public final int height;
    //public final int sides;
    public final Cell[][] cells;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        //this.sides = sides;
        cells = new Cell[width][height];

        reset();
    }

    public void reset() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = new Cell(this, x, y);
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = cells[x][y];

                for (int side = 0; side < cell.sides; side++) {
                    Cell neighbor = getNeighbor(cell, side);
                    if (neighbor == null) continue;

                    cell.edges[side] = new Edge(cell, neighbor, side);
                }
            }
        }
    }

    public boolean contains(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public double euclidean(Cell a, Cell b) {
        return a.euclidean(b);
    }

    public double manhattan(Cell a, Cell b) {
        return a.manhattan(b);
    }

    public void connect(Edge n) {
        int source = (int) Math.pow(2, n.side);
        int opp = getOppositeIndex(n.source, n.side);
        int target = (int) Math.pow(2, opp);

        //System.out.println("S: " + n.source + " T: " + n.target);

        n.source.walls |= source;
        n.target.walls |= target;
    }


    public void disconnect(Edge n) {
        int source = (int) Math.pow(2, n.side);
        int opp = getOppositeIndex(n.source, n.side);
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
        int source = (int) Math.pow(2, n.side);
        int opp = getOppositeIndex(n.source, n.side);
        int target = (int) Math.pow(2, opp);

        return (n.source.walls & source) == source && (n.target.walls & target) == target;
    }

    public abstract int getSides(Cell cell);

    public abstract Point getOffset(Cell cell, int index);

    public int getOppositeIndex(Cell cell, int index) {
        int index2 = (index + (cell.sides / 2)) % cell.sides;

        //System.out.println("Before: " + side + " After: " + index2);

        return index2;
        //return cell.x % 2 == cell.y % 2 ? identicalOffsets[side] : distinctOffsets[side];
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

        for (int i = 0; i < cell.sides; i++) {
            Cell n = getNeighbor(cell, i);
            if (n == null) continue;
            adjacent.add(n);
        }

        return adjacent.toArray(new Cell[adjacent.size()]);
    }

    // paint

    public final void resize(Rectangle area) {
        PointD d = getScaledSize();
        double sWidth = d.x;
        double sHeight = d.y;

        int cellSize = (int) Math.floor(Math.min((double) area.width / sWidth, (double) area.height / sHeight));

        resize(cellSize);
    }

    public abstract void resize(int size);

    public abstract PointD getScaledSize();

    public abstract Point getSize();

    public abstract Point getLocation(Cell cell);

    public abstract Point getPoint(Cell cell, int index);

    public final Polygon getSide(Cell cell, int index) {
        java.awt.Point a = getPoint(cell, index);
        java.awt.Point b = getPoint(cell, (index + 1) % cell.sides);

        return new Polygon(
                new int[]{a.x, b.x},
                new int[]{a.y, b.y},
                2
        );
    }

    public final Polygon getPolygon(Cell cell) {
        int[] xs = new int[cell.sides];
        int[] ys = new int[cell.sides];
        for (int i = 0; i < cell.sides; i++) {
            java.awt.Point p = getPoint(cell, i);

            xs[i] = p.x;
            ys[i] = p.y;
        }

        return new Polygon(xs, ys, cell.sides);
    }

  /*  public JSONObject toJson() {

        JSONObject grid = new JSONObject();

        grid.put("type", "null");

        JSONArray cellsArray = new JSONArray();
        grid.put("cells", cellsArray);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell c = cells[x][y];

                JSONObject cell = new JSONObject();
                cell.put("x", x);
                cell.put("y", y);
                cell.put("walls", c.walls);

                cellsArray.put(cell);
            }
        }

        return grid;
    }*/


}
