package com.pucilowski.navigation.environment;

import com.pucilowski.navigation.logic.algorithm.Algorithm;
import com.pucilowski.navigation.logic.grid.Grid;
import com.pucilowski.navigation.logic.grid.grids.SqGrid;
import com.pucilowski.navigation.environment.business.AlgorithmHandler;
import com.pucilowski.navigation.environment.business.EventHandler;

/**
 * Created by martin on 10/12/13.
 */
public class Environment {

    // data
    public Grid grid;
    public Algorithm algo;

    // logic
    public EventHandler events;
    public AlgorithmHandler logic;

    // ui


    public Environment() {

        grid = new SqGrid(40, 30);

          logic = new AlgorithmHandler(this);
        events = new EventHandler(this);


        //final Cell start = grid.cells[grid.width/2][grid.height/2];
        //final Cell goal = grid.cells[grid.width-1][grid.height-1];


    }


    public void onRefresh() {

    }

}
