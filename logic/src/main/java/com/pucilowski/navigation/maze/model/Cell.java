package com.pucilowski.navigation.maze.model;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public class Cell {


    public final int x;
    public final int y;

    public final int sides;
    public final Neighborship[] neighborships;

    public int walls = 0;

    public int depth = 0;
    Color c = Color.WHITE;

    public Cell(int x, int y, int sides) {
        this.x = x;
        this.y = y;
        this.sides = sides;
        //this.neighborships = new Cell[sides];
        this.neighborships = new Neighborship[sides];

        //wall = new boolean[sides];
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
