package com.pucilowski.navigation;

import com.pucilowski.navigation.maze.algorithm.Algorithm;
import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.grids.SqGrid;
import com.pucilowski.navigation.ui.GUI;
import com.pucilowski.navigation.ui.business.AlgorithmHandler;
import com.pucilowski.navigation.ui.business.EventHandler;

/**
 * Created by martin on 10/12/13.
 */
public class Mazing {

    // data
    public Grid grid;
    public Algorithm algo;

    // logic
    public EventHandler events;
    public AlgorithmHandler logic;

    // ui
    public GUI gui;


    public Mazing() {

        grid = new SqGrid(47, 29);

        gui = new GUI(this);

        logic = new AlgorithmHandler(this);
        events = new EventHandler(this);


        //final Cell start = grid.cells[grid.width/2][grid.height/2];
        //final Cell goal = grid.cells[grid.width-1][grid.height-1];


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {



            /*    final Generator dfs = new Prims(grid);
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


                algo = new AStar(grid, start,goal, );

                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //grid.toIsland(goal);

                while (algo.state == State.WORKING && gui.frame.isVisible()) {

                    algo.step();


                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    gui.frame.repaint();

                }
*//*
                Cell[] p = search.path;

                for (int i = 0; i < p.length; i++) {
                    Cell c = p[i];
                    System.out.println(c);
                }*//*

                System.out.println("done");*/

            }
        });
        t.start();


    }


}
