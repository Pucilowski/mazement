package com.pucilowski.navigation.ui.swing.dialogs.enums;

import com.pucilowski.navigation.maze.algorithm.Generator;
import com.pucilowski.navigation.maze.algorithm.generation.DFS;
import com.pucilowski.navigation.maze.algorithm.generation.Prims;
import com.pucilowski.navigation.maze.grid.Grid;

/**
 * Created by martin on 24/12/13.
 */
public enum MazeGenerator {
    SQUARE("Depth First Search") {
        @Override
        public Generator newGenerator(Grid grid){
            return new DFS(grid);
        }
    },
    HEXAGON("Prim's") {
        @Override
        public Generator newGenerator(Grid grid){
            return new Prims(grid);
        }
    };

    final String label;

    MazeGenerator(String label) {
        this.label = label;
    }

    public Generator newGenerator(Grid grid){
        return null;
    }


    @Override
    public String toString() {
        return label;
    }

}
