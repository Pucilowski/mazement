package com.pucilowski.navigation.environment;

import com.pucilowski.navigation.environment.business.EventHandler;
import com.pucilowski.navigation.environment.business.ProcessHandler;
import com.pucilowski.navigation.environment.enums.GridType;
import com.pucilowski.navigation.mazes.grid.Grid;
import com.pucilowski.navigation.mazes.grid.grids.SqGrid;
import com.pucilowski.navigation.mazes.process.Process;

/**
 * Created by martin on 10/12/13.
 */
public abstract class Environment {

    public GridType gridType = GridType.SQUARE;
    public Grid grid;
    public Process process;

    public EventHandler events;
    public ProcessHandler logic;

    public Environment() {
        gridType = GridType.SQUARE;
        grid = new SqGrid(80, 50);

        logic = new ProcessHandler(this);
        events = new EventHandler(this);
    }

    public void reset() {
        grid = gridType.newMaze(80, 50);
    }

    public abstract void onRefresh();
}
