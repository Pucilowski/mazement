package com.pucilowski.navigation.ui.swing.dialogs.enums;

import com.pucilowski.navigation.maze.algorithm.Pathfinder;
import com.pucilowski.navigation.maze.algorithm.pathfinding.AStar;
import com.pucilowski.navigation.maze.grid.Grid;

/**
 * Created by martin on 24/12/13.
 */
public enum MazePathfinder {

    ASTAR("A* Star") {
        @Override
        public Pathfinder newPathfinder(Grid grid){
            return new AStar(grid);
        }
    };

    final String label;

    MazePathfinder(String label) {
        this.label = label;
    }

    public Pathfinder newPathfinder(Grid grid){
        return null;
    }


    @Override
    public String toString() {
        return label;
    }

}
