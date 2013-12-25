package com.pucilowski.navigation;

import com.pucilowski.navigation.maze.algorithm.Algorithm;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.grids.HexGrid;
import com.pucilowski.navigation.maze.grid.grids.SqGrid;
import com.pucilowski.navigation.maze.grid.grids.TriGrid;
import com.pucilowski.navigation.ui.GUI;
import com.pucilowski.navigation.ui.business.AlgorithmHandler;
import com.pucilowski.navigation.ui.business.EventHandler;

/**
 * Created by martin on 10/12/13.
 */
public class Main {

    // data
    public Grid grid;
    public Algorithm algo;

    // logic
    public EventHandler events;
    public AlgorithmHandler logic;

    // ui
    public GUI gui;


    public Main() {

        grid = new SqGrid(40, 30);

        gui = new GUI(this);

        logic = new AlgorithmHandler(this);
        events = new EventHandler(this);


        //final Cell start = grid.cells[grid.width/2][grid.height/2];
        //final Cell goal = grid.cells[grid.width-1][grid.height-1];



    }
    public static void main(String[] args) {
        new Main();
    }

}
