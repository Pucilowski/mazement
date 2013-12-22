package com.pucilowski.navigation.maze.pathfinding.model;

import java.awt.*;

/**
 * Created by martin on 15/11/13.
 */
public class Vertex {

    public final int x;
    public final int y;

    public Vertex parent;
    public int depth;
    public double g_score;
    public double f_score;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double euclidean(Vertex v) {
        double dx = v.x - x;
        double dy = v.y - y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public double manhattan(Vertex v) {
        return Math.abs(v.x - x) + Math.abs(v.y - y);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vertex)) return false;

        Vertex v = (Vertex) o;

        return v.x == x && v.y == y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }


    public Point toPoint() {
        return new Point(x, y);
    }


}
