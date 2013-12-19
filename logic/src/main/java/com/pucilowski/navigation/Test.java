package com.pucilowski.navigation;

import com.pucilowski.navigation.maze.algorithms.generation.StackDFS;
import com.pucilowski.navigation.maze.model.grid.Grid;
import com.pucilowski.navigation.maze.model.grid.SquareGrid;

/**
 * Created by martin on 19/12/13.
 */
public class Test {

    public static void main(String[] args) {

        Grid grid = new SquareGrid(16,16);

        StackDFS dfs = new StackDFS(grid);



        grid.display();


    }

}
