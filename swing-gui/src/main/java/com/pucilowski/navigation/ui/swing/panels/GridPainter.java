package com.pucilowski.navigation.ui.swing.panels;

import com.pucilowski.navigation.mazes.grid.Cell;
import com.pucilowski.navigation.mazes.grid.Grid;

import java.awt.*;

/**
 * Created by martin on 24/12/13.
 */
public class GridPainter {

    Grid grid;

    public GridPainter(Grid grid) {
        this.grid = grid;
    }

    public void paint(PaintTile tile) {

        int size = 10;

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {

                Cell cell = grid.cells[x][y];

                Point location = grid.getLocation(cell);
                int px = 8 + location.x;
                int py = 12 + location.y;

                Polygon polygon = grid.getPolygon(cell);
                polygon.translate(px, py);


                tile.paint(null, location, polygon);

            }
        }

    }

    ;

    interface PaintTile {
        public void paint(Graphics g, Point location, Polygon polygon);
    }


}
