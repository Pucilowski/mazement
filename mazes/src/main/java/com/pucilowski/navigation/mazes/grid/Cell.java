package com.pucilowski.navigation.mazes.grid;

import java.util.ArrayList;

/**
 * Created by martin on 10/12/13.
 */
public class Cell {

    public final Grid grid;
    public final int x, y;
    public final int sides;
    public final Edge[] edges;

    public int walls = 0;

    public Cell(Grid grid, int x, int y) {
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.sides = grid.getSides(this);
        this.edges = new Edge[sides];
    }

    public Edge getEdge(int side) {
        return edges[side];
    }

    public Edge[] getEdges() {
        ArrayList<Edge> edgeList = new ArrayList<Edge>();

        //for (int i = 0; i < edges.length; i++) {
        for (Edge edge : edges) {
            if (edge == null) continue;
            edgeList.add(edge);
        }

        return edgeList.toArray(new Edge[edgeList.size()]);
    }

    public double euclidean(Cell v) {
        double dx = v.x - x;
        double dy = v.y - y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public double manhattan(Cell v) {
        return Math.abs(v.x - x) + Math.abs(v.y - y);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Cell)) return false;

        Cell cell = (Cell) other;
        return x == cell.x && y == cell.y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
