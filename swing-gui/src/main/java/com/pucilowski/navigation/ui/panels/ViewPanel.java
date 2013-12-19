package com.pucilowski.navigation.ui.panels;

import com.pucilowski.navigation.maze.model.Cell;
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


    @Override
    public void paintComponent(Graphics g) {
        Grid grid = getGrid();

        int w = grid.width * 2 + 1;
        int h=  grid.height * 2 + 1;



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

    @Override
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
