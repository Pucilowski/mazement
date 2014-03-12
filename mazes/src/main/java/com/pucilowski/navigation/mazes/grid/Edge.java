package com.pucilowski.navigation.mazes.grid;

/**
 * Created by martin on 19/12/13.
 */
public class Edge {

    public final Cell source;
    public final Cell target;
    public final int side;

    public Edge(Cell source, Cell target, int side) {
        this.source = source;
        this.target = target;
        this.side = side;
    }

}
