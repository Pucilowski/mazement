package com.pucilowski.navigation.ui.swing.dialogs;

import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.grids.HexGrid;
import com.pucilowski.navigation.maze.grid.grids.SqGrid;
import com.pucilowski.navigation.maze.grid.grids.TriGrid;

/**
 * Created by martin on 24/12/13.
 */
public enum MazeType {
    SQUARE("Square") {
        @Override
        public Grid newMaze(int width, int height) {
            return new SqGrid(width, height);
        }
    },
    HEXAGON("Hexagonal") {
        @Override
        public Grid newMaze(int width, int height) {
            return new HexGrid(width, height);
        }
    },
    TRIANGLE("Triangular") {
        @Override
        public Grid newMaze(int width, int height) {
            return new TriGrid(width, height);
        }
    };

    final String label;

    MazeType(String label) {
        this.label = label;
    }

    public Grid newMaze(int width, int height){
        return null;
    }


    @Override
    public String toString() {
        return label;
    }

}
