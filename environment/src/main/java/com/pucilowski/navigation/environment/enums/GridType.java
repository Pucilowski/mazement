package com.pucilowski.navigation.environment.enums;

import com.pucilowski.navigation.mazes.grid.Grid;
import com.pucilowski.navigation.mazes.grid.grids.HexGrid;
import com.pucilowski.navigation.mazes.grid.grids.SqGrid;
import com.pucilowski.navigation.mazes.grid.grids.TriGrid;
import com.pucilowski.navigation.mazes.grid.grids.UpsilonGrid;

/**
 * Created by martin on 24/12/13.
 */
public enum GridType {
    SQUARE("Square") {
        @Override
        public Grid newMaze(int width, int height) {
            return new SqGrid(width, height);
        }
    },
    TRIANGLE("Triangular") {
        @Override
        public Grid newMaze(int width, int height) {
            return new TriGrid(width, height);
        }
    },
    HEXAGON("Hexagonal") {
        @Override
        public Grid newMaze(int width, int height) {
            return new HexGrid(width, height);
        }
    },

    UPSILON("Upsilon") {
        @Override
        public Grid newMaze(int width, int height) {
            return new UpsilonGrid(width, height);
        }
    };

    final String label;

    GridType(String label) {
        this.label = label;
    }

    public Grid newMaze(int width, int height) {
        return null;
    }


    @Override
    public String toString() {
        return label;
    }

}
