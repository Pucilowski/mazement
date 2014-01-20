package com.pucilowski.navigation.environment;

import com.pucilowski.navigation.environment.business.EventHandler;
import com.pucilowski.navigation.environment.business.ProcessHandler;
import com.pucilowski.navigation.environment.enums.GridType;
import com.pucilowski.navigation.environment.events.NewGridEvent;
import com.pucilowski.navigation.logic.grid.Grid;
import com.pucilowski.navigation.logic.grid.grids.HexGrid;
import com.pucilowski.navigation.logic.grid.grids.SqGrid;
import com.pucilowski.navigation.logic.grid.grids.UpsilonGrid;
import com.pucilowski.navigation.logic.process.Process;

/**
 * Created by martin on 10/12/13.
 */
public abstract class Environment {

    public GridType gridType;
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


    public abstract void onRefresh();
}
