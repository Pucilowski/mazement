package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by martin on 10/12/13.
 */
public class HexGrid extends Grid {

    public static final Point NORTH = new Point(0, -1);
    public static final Point SOUTH = new Point(0, 1);

  /*  public static final Point[] offsets = {
            new Point(-1, 0), // nw
            new Point(-1, 1), //ne

          EAST, // e
            WEST, // w

            new Point(1, 0), // ne
            new Point(1, 1), // se
    };*/


    public static final Point[] offsets = {


            new Point(1, -1), // ne
            new Point(1, 0), // se

            SOUTH,

            new Point(-1, 0), // sw
            new Point(-1, -1), // nw

            NORTH,
    };

    public HexGrid(int width, int height) {
        super(width, height, 6);
    }

    @Override
    public Point getOffset(Cell cell,int index) {
        //return offsets[index];
        int x =  offsets[index].x;
        int y =  offsets[index].y;
        if(cell.x%2==1) y++;

        return new Point(x,y);
    }

    public Cell getAdjacentTile(Cell cell, int index) {

        Point o = getOffset(cell,index);

        int x = cell.x + o.x;
        int y = cell.y + o.y;
        if(cell.x%2==1) y++;

        if (!contains(x, y)) return null;

        return cells[x][y];
    }

    @Override
    public Cell[] getAdjacentTiles(Cell cell) {
        ArrayList<Cell> adjacent = new ArrayList<Cell>();

        for (int i = 0; i < sides; i++) {
            Cell n = getAdjacentTile(cell, i);
            if (n == null) continue;
            adjacent.add(n);
        }

        return adjacent.toArray(new Cell[adjacent.size()]);
    }

    @Override
    public void display() {

    }
}
