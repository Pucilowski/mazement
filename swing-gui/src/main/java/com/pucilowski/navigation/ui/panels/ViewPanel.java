package com.pucilowski.navigation.ui.panels;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.grid.BinaryGrid;
import com.pucilowski.navigation.maze.model.grid.Grid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by martin on 11/12/13.
 */
public abstract class ViewPanel extends JPanel {

    public ViewPanel() {
        setBackground(Color.RED);
    }

    public abstract Grid getGrid();

    public void paintComponent(Graphics g) {
        BinaryGrid grid = getGrid().binaryGrid;


        int w = grid.width;
        int h = grid.height;


        int cw = getWidth() / w;
        int ch = getHeight() / h;

        int size = Math.min(cw, ch);

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                boolean walkable = grid.passage[x][y];

                g.setColor(Color.BLACK);
                if (walkable) g.setColor(Color.WHITE);

                int d = 0;
                if (x % 2 == 0) d = ch / 2;

                int px = size * x;
                int py = size * y + d;

                if (walkable)
                    g.drawRect(px, py, size - 1, size - 1);
                else g.fillRect(px, py, cw, ch);


            }
        }

    }

    public void paintComponent3(Graphics g) {
        Grid grid = getGrid();

        int w = grid.width * 2 + 1;
        int h = grid.height * 2 + 1;


        int cw = getWidth() / w;
        int ch = getHeight() / h;

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                Cell cell = grid.cells[x][y];

                g.setColor(Color.BLACK);
                if (cell.walkable) g.setColor(Color.WHITE);

                int px = cw * x;
                int py = ch * y;

                g.drawRect(px, py, cw, ch);


            }
        }

    }

    public void paintComponent2(Graphics g) {
        Grid grid = getGrid();

        int w = grid.width;
        int h = grid.height;

        int cw = getWidth() / w;
        int ch = getHeight() / h;

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                Cell cell = grid.cells[x][y];

                g.setColor(Color.BLACK);
                if (cell.walkable) g.setColor(Color.WHITE);

                int px = cw * x;
                int py = ch * y;

                g.drawRect(px, py, cw, ch);


            }
        }

    }

}
