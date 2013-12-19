package com.pucilowski.navigation.maze.algorithms.pathfinding.model;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by martin on 15/11/13.
 */
public abstract class Graph {

    private final ArrayList<Vertex> vertices = new ArrayList<Vertex>();

    public abstract Vertex[] getValencies(Vertex current);

    public Graph() {

    }

    public Graph(Grid grid) {

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                Cell c = grid.cells[x][y];

                Vertex v = new Vertex(c.x, c.y);
                vertices.add(v);

            }
        }
    }

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
