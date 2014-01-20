package com.pucilowski.navigation.environment.enums;

import com.pucilowski.navigation.logic.process.Generator;
import com.pucilowski.navigation.logic.process.generation.DFGen;
import com.pucilowski.navigation.logic.process.generation.Prims;
import com.pucilowski.navigation.logic.grid.Grid;

/**
 * Created by martin on 24/12/13.
 */
public enum MazeGenerator {
    SQUARE("Depth First") {
        @Override
        public Generator newGenerator(Grid grid){
            return new DFGen(grid);
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
