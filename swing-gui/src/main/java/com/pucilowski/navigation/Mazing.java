package com.pucilowski.navigation;

import com.pucilowski.navigation.maze.algorithm.Algorithm;
import com.pucilowski.navigation.maze.algorithm.Generator;
import com.pucilowski.navigation.maze.algorithm.State;
import com.pucilowski.navigation.maze.algorithm.generation.Prims;
import com.pucilowski.navigation.maze.algorithm.generation.misc.StepListener;
import com.pucilowski.navigation.maze.algorithm.pathfinding.Distance;
import com.pucilowski.navigation.maze.algorithm.pathfinding.ReStar;
import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.grids.HexGrid;
import com.pucilowski.navigation.ui.GUI;
import com.pucilowski.navigation.ui.events.Event;
import com.pucilowski.navigation.ui.events.EventHandler;

import java.util.LinkedList;

/**
 * Created by martin on 10/12/13.
 */
public class Mazing   {

    public Grid grid;

    public Algorithm algo;



    GUI gui;

    public EventHandler events;

    public Mazing() {

        events = new EventHandler(this);

        grid = new HexGrid(47, 29);

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


                algo = new ReStar(grid, start,goal, new Distance() {
                    @Override
                    public double weight(Cell a, Cell b) {
                        return a.euclidean(b);
                    }

                    @Override
                    public double heuristic(Cell a, Cell b) {
                        return 0;
                        //return a.manhattan(b)*2;
                    }
                });

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
