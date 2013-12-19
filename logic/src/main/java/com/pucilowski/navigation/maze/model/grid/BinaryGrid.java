package com.pucilowski.navigation.maze.model.grid;

/**
 * Created by martin on 19/12/13.
 */
public class BinaryGrid {

    final int width;
    final int height;
    final boolean[][] wall;

    public BinaryGrid(Grid grid) {
        this.width = grid.width * 2 + 1;
        this.height = grid.height * 2 + 1;
        this.wall = new boolean[width][height];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {



            }
        }

    }

}
