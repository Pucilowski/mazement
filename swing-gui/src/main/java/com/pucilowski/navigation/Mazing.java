package com.pucilowski.navigation;

import com.pucilowski.navigation.maze.algorithm.Algorithm;
import com.pucilowski.navigation.maze.algorithm.Generator;
import com.pucilowski.navigation.maze.algorithm.State;
import com.pucilowski.navigation.maze.algorithm.generation.*;
import com.pucilowski.navigation.maze.algorithm.generation.misc.StepListener;
import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.grids.HexGrid;
import com.pucilowski.navigation.maze.grid.grids.SqGrid;
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

        grid = new SqGrid(120, 45);

        final Cell start = grid.cells[grid.width/2][grid.height/2];
        final Cell goal = grid.cells[grid.width-1][grid.height-1];

        gui = new GUI(this);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {



                final Generator dfs = new Prims(grid);
                dfs.step = new StepListener() {
                    @Override
                    public void onStep(Cell cell) {
                        //gui.frame.repaint();

                    }
                };

                algo = dfs;


                dfs.start();

                while (dfs.state == State.WORKING && gui.frame.isVisible()) {
                    dfs.step();


                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    gui.frame.repaint();

                }


                ReStar search = new ReStar(grid, start,goal, new Distance() {
                    @Override
                    public double weight(Cell a, Cell b) {
                        return 1;//a.euclidean(b);
                    }

                    @Override
                    public double heuristic(Cell a, Cell b) {
                        return a.manhattan(b);
                    }
                });

                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                algo = search;
                //grid.toIsland(grid.cells[29][19]);

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
