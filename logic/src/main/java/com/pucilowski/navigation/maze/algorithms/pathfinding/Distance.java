package com.pucilowski.navigation.maze.algorithms.pathfinding;

import com.pucilowski.navigation.maze.algorithms.pathfinding.model.Vertex;

/**
 * Created by martin on 15/11/13.
 */
public interface Distance {

    public double weight(Vertex a, Vertex b);

    public double heuristic(Vertex a, Vertex b);

}