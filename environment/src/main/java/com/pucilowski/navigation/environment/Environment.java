package com.pucilowski.navigation.environment;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.pucilowski.navigation.environment.business.TaskHandler;
import com.pucilowski.navigation.environment.enums.GridType;
import com.pucilowski.navigation.environment.events.Event;
import com.pucilowski.navigation.environment.events.GenerateMazeEvent;
import com.pucilowski.navigation.environment.events.NewGridEvent;
import com.pucilowski.navigation.environment.events.SolveMazeEvent;
import com.pucilowski.navigation.mazes.grid.Grid;
import com.pucilowski.navigation.mazes.grid.grids.SqGrid;
import com.pucilowski.navigation.mazes.process.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by martin on 10/12/13.
 */

@Component
public abstract class Environment {

    public GridType gridType = GridType.SQUARE;
    public Grid grid = new SqGrid(20, 20);

    @Autowired
    public EventBus bus;
    public final TaskHandler logic;

    public Environment() {
        System.out.println("Environment()" + this);

        //bus = new EventBus();
        logic = new TaskHandler(this);
    }

    public void init() {
        bus.register(this);
    }

    public void runTask(Task task) {
        logic.setTask(task);
        task.start();
    }

    private void killTask() {
        logic.setTask(null);
    }

    public abstract void onRefresh();

    @Subscribe
    public void newGrid(NewGridEvent event) {
        System.out.println("new grid: " + event.toString());

        killTask();

        gridType = event.type;
        grid = event.type.newMaze(event.width, event.height);

        onRefresh();
    }


    @Subscribe
    public void generateMaze(GenerateMazeEvent event) {
        System.out.println("new maze: " + event.toString());

        grid.reset();

        Task p = event.gen.newGenerator(grid);
        runTask(p);

        onRefresh();
    }

    @Subscribe
    public void solveMaze(SolveMazeEvent event) {
        System.out.println("solve maze: " + event.toString());

        runTask(event.gen.newPathfinder(grid));
    }

    @Subscribe
    public void onEvent(Event event) {
        System.out.println("Event wildcard: " + event.toString());

        onRefresh();
    }

}
