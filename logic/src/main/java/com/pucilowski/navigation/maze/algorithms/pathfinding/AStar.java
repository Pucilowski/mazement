package com.pucilowski.navigation.maze.algorithms.pathfinding;

import com.pucilowski.navigation.maze.algorithms.pathfinding.model.Graph;
import com.pucilowski.navigation.maze.algorithms.pathfinding.model.Vertex;

/**
 * Created by martin on 10/12/13.
 */
public class AStar extends Pathfinder {

    final Distance distance;

    public AStar(Graph graph, Vertex start, Vertex end, Distance distance) {
        super(graph,start, end);

        this.distance=distance;
    }

}
