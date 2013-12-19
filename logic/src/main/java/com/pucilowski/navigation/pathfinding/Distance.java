package com.pucilowski.navigation.pathfinding;

import com.pucilowski.navigation.pathfinding.model.Vertex;

/**
 * Created by martin on 15/11/13.
 */
public interface Distance {

    public double weight(Vertex a, Vertex b);

    public double heuristic(Vertex a, Vertex b);

}
