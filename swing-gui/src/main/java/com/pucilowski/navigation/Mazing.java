package com.pucilowski.navigation;

import com.pucilowski.navigation.maze.algorithm.Algorithm;
import com.pucilowski.navigation.maze.algorithm.Generator;
import com.pucilowski.navigation.maze.algorithm.State;
import com.pucilowski.navigation.maze.algorithm.generation.*;
import com.pucilowski.navigation.maze.algorithm.generation.misc.StepListener;
import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.grids.TriGrid;
import com.pucilowski.navigation.maze.algorithm.pathfinding.Distance;
import com.pucilowski.navigation.maze.algorithm.pathfinding.ReStar;
import com.pucilowski.navigation.ui.GUI;

/**
 * Created by martin on 10/12/13.
 */
public class Mazing   {

    public Grid grid;

    public Algorithm algo;

    GUI gui;

    public Mazing() {

        grid = new TriGrid(120, 45);
        gui = new GUI(this);

        final Generator dfs = new Prims(grid);
        dfs.step = new StepListener() {
            @Override
            public void onStep(Cell cell) {
                //gui.frame.repaint();

            }
        };

        algo = dfs;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                dfs.start();

                while (dfs.state == State.WORKING && gui.frame.isVisible()) {
                    dfs.step();


                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    gui.frame.repaint();

                }

                grid.toIsland(grid.cells[29][19]);

                ReStar search = new ReStar(grid, grid.cells[0][0], grid.cells[29][19], new Distance() {
                    @Override
                    public double weight(Cell a, Cell b) {
                        return 1;//a.euclidean(b);
                    }

                    @Override
                    public double heuristic(Cell a, Cell b) {
                        return a.manhattan(b);
                    }
                });



                algo = search;

                while (search.state == State.WORKING && gui.frame.isVisible()) {

                    search.step();


                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    gui.frame.repaint();

                }
/*
                Cell[] p = search.path;

                for (int i = 0; i < p.length; i++) {
                    Cell c = p[i];
                    System.out.println(c);
                }*/

                System.out.println("done");

            }
        });
        t.start();


    }


}
