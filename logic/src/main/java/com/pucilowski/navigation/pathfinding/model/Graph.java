package com.pucilowski.navigation.pathfinding.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by martin on 15/11/13.
 */
public abstract class Graph {

    private final ArrayList<Vertex> vertices = new ArrayList<Vertex>();


    public abstract Vertex[] getValencies(Vertex current);

    public void addVertex(Vertex v) {
        //if (vertices.contains(v)) return;
        vertices.add(v);
        //System.out.println("Added: " + v + " flags: " + v.passage);
    }

    public Vertex getVertex(int x, int y) {
        for (Vertex v : vertices) {
            if (v.x == x && v.y == y) return v;
        }
        return null;
    }

    public Vertex getVertex(Point p) {
        for (Vertex v : vertices) {
            if (v.x == p.x && v.y == p.y) return v;
        }
        return null;
    }

}
