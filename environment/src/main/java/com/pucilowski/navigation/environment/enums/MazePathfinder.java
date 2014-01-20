package com.pucilowski.navigation.environment.enums;

import com.pucilowski.navigation.logic.process.Pathfinder;
import com.pucilowski.navigation.logic.process.pathfinding.AStar;
import com.pucilowski.navigation.logic.grid.Grid;
import com.pucilowski.navigation.logic.process.pathfinding.DFSearch;

/**
 * Created by martin on 24/12/13.
 */
public enum MazePathfinder {

    DFSEARCH("Depth First") {
        @Override
        public Pathfinder newPathfinder(Grid grid){
            return new DFSearch(grid);
        }
    },
    ASTAR("A* Star Search") {
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
