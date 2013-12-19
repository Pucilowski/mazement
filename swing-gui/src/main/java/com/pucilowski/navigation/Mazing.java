package com.pucilowski.navigation;

import com.pucilowski.navigation.maze.algorithms.generation.AbstractGenerator;
import com.pucilowski.navigation.maze.algorithms.generation.BFS;
import com.pucilowski.navigation.maze.algorithms.generation.Prim;
import com.pucilowski.navigation.maze.algorithms.generation.StackDFS;
import com.pucilowski.navigation.maze.algorithms.generation.misc.StepListener;
import com.pucilowski.navigation.maze.model.Cell;
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

        grid = new SquareGrid(20, 15);
        gui = new GUI(this);


        final AbstractGenerator dfs = new StackDFS(grid);
        dfs.step = new StepListener() {
            @Override
            public void onStep(Cell cell) {
                gui.frame.repaint();

                if (cell.depth > grid.maxDepth) grid.maxDepth = cell.depth;
            }
        };

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                dfs.start();
            }
        });
        t.start();


    }
}
