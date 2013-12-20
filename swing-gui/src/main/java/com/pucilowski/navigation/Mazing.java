package com.pucilowski.navigation;

import com.pucilowski.navigation.maze.algorithms.Algorithm;
import com.pucilowski.navigation.maze.algorithms.generation.Generator;
import com.pucilowski.navigation.maze.algorithms.generation.Prims;
import com.pucilowski.navigation.maze.algorithms.generation.RecursiveDFS;
import com.pucilowski.navigation.maze.algorithms.generation.StackDFS;
import com.pucilowski.navigation.maze.algorithms.generation.misc.StepListener;
import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.grid.Grid;
import com.pucilowski.navigation.maze.model.grid.HexGrid;
import com.pucilowski.navigation.maze.model.grid.SqGrid;
import com.pucilowski.navigation.maze.model.grid.TriGrid;
import com.pucilowski.navigation.ui.GUI;

/**
 * Created by martin on 10/12/13.
 */
public class Mazing {

    public Grid grid;

    GUI gui;

    public Mazing() {

        grid = new SqGrid(35, 25);
        gui = new GUI(this);

        final Generator dfs = new Prims(grid);
        dfs.step = new StepListener() {
            @Override
            public void onStep(Cell cell) {
                gui.frame.repaint();

                //if (cell.depth > grid.maxDepth) grid.maxDepth = cell.depth;
            }
        };

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                dfs.start();

                while(dfs.state == Algorithm.State.WORKING) {
                    dfs.step();


            /*        try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/

                }

                System.out.println("done");

            }
        });
        t.start();


    }
}
