package com.pucilowski.navigation.logic.algorithm;

import com.pucilowski.navigation.logic.grid.Cell;
import com.pucilowski.navigation.logic.grid.Grid;

import java.awt.*;

/**
 * Created by martin on 20/12/13.
 */
public abstract class Algorithm<M extends CellMeta> {
    public State state = State.WORKING;

    public final Grid grid;

    //HashMap<Cell, CellMeta> data = new HashMap<Cell, CellMeta>();
    final M[][] data;

    public Algorithm(Grid grid) {
        this.grid = grid;

        data = (M[][]) new CellMeta[grid.width][grid.height];

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                //cells[x][y] = new Cell(x, y, sides);
                Cell c = grid.cells[x][y];
                data[x][y] = newMeta(c);
            }
        }
    }

    public M newMeta(Cell cell) {
        return (M)new CellMeta(cell);
    }

    public M getMeta(Cell cell) {
        return data[cell.x][cell.y];
    }

    public abstract void start();

    public abstract void step();

    public void notify(Event e) {

    }

    public Color getColor(Cell cell){
        return null;
    }
    public  Cell getCurrent() {
        return null;
    };

    public void paint(Graphics g, Cell cell, Point p, Polygon p2) {

    }

}
