package com.pucilowski.navigation;

import com.pucilowski.navigation.maze.algorithms.generation.StackDFS;
import com.pucilowski.navigation.maze.model.grid.Grid;
import com.pucilowski.navigation.maze.model.grid.HexGrid;
import com.pucilowski.navigation.maze.model.grid.SquareGrid;
import com.pucilowski.navigation.ui.GUI;

/**
 * Created by martin on 10/12/13.
 */
public class Mazing {

    public Grid grid;

    GUI gui;

    public Mazing() {

        grid = new HexGrid(20,15);

        StackDFS dfs = new StackDFS(grid);

        gui = new GUI(this);

    }
}
