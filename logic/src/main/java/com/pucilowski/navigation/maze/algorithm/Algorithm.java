package com.pucilowski.navigation.maze.algorithm;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.awt.*;

/**
 * Created by martin on 20/12/13.
 */
public abstract class Algorithm<M extends CellMeta> {

    public final Grid grid;

    //HashMap<Cell, CellMeta> data = new HashMap<Cell, CellMeta>();
    final CellMeta[][] data;

    public Algorithm(Grid grid) {
        this.grid = grid;

        data = new CellMeta[grid.width][grid.height];

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                //cells[x][y] = new Cell(x, y, sides);
                Cell c = grid.cells[x][y];
                data[x][y] = newMeta(c);
            }
        }
    }

    public CellMeta newMeta(Cell cell) {
        return new CellMeta(cell);
    }

    public CellMeta getMeta(Cell cell) {
        return data[cell.x][cell.y];
    }

    public abstract void start();

    public abstract void step();

    public void notify(Event e) {

    }

    //public abstract void paint(Graphics g, Cell cell, Point p, Polygon p2);

}
