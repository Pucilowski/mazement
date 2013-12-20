package com.pucilowski.navigation.maze.model;

/**
 * Created by martin on 19/12/13.
 */
public class Edge {

    public final Cell source;
    public final Cell target;
    public final int index;



    public Edge(Cell source, Cell target, int index) {
        this.source = source;
        this.target = target;
        this.index = index;


    }


}
