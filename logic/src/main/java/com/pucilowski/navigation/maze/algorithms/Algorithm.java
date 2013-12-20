package com.pucilowski.navigation.maze.algorithms;

/**
 * Created by martin on 20/12/13.
 */
public abstract class Algorithm {


    public abstract void start();

    public abstract void step();
    public enum State {
        WORKING,
        FAILED,
        SUCCESS
    }
}
