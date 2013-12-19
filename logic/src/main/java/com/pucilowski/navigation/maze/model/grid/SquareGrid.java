package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by martin on 10/12/13.
 */
public class SquareGrid extends Grid {

    public static final Point[] offsets = {
            new Point(0, -1),
            new Point(1, 0),
            new Point(0, 1),
            new Point(-1, 0),
    };

    public SquareGrid(int width, int height) {
        super(width, height, 4);
    }




    @Override
    public Point getOffset(Cell cell,int index) {
        return offsets[index];
    }



    /**
     * sourced from rosettacode
     */
    public void display() {

        int x = width;
        int y = height;


        for (int i = 0; i < y; i++) {
            // draw the north edge
            for (int j = 0; j < x; j++) {
                System.out.print((cells[j][i].walls & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < x; j++) {
                System.out.print((cells[j][i].walls & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < x; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
