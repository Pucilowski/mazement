package com.pucilowski.navigation.ui.panels;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Edge;
import com.pucilowski.navigation.maze.model.grid.Grid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by martin on 11/12/13.
 */
public abstract class GridPanel extends JPanel {

    public GridPanel() {

    }

    public abstract Grid getGrid();

    @Override
    public void paintComponent(Graphics g) {
        Grid grid = getGrid();


        int w = grid.width;
        int h = grid.height;


        int size = Math.min(getWidth() / w, getHeight() / h);
        //int size = 24;

        Color[] colors = {
                Color.RED,
                Color.GREEN,
                Color.YELLOW,
                Color.BLUE,
                Color.MAGENTA,
                Color.CYAN
        };

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                //if(x%2!=1 )continue;

                Cell cell = grid.cells[x][y];
                //boolean walkable = grid.passage[x][y];

                Point location = grid.getLocation(cell, size);
                Polygon polygon = grid.getPolygon(cell, size);

                int px = 8 + location.x;
                int py = 12 + location.y;



/*                g.setColor(Color.PINK);
                if(cell.x % 2 == cell.y % 2) {
                    g.setColor(Color.ORANGE);
                }*/

                //px = 8 + size/2 * x;
                //py = 8 + size * y;
                //p.translate(px,py);
                //g.fillPolygon(p);

                //g.setColor(Color.BLACK);
                //g.drawString(cell.getEdges().length+"",px, py+32);

                for (int index = 0; index < grid.sides; index++) {
                    Edge n = cell.edges[index];

                    if (n != null && grid.isConnected(n)) {
                        //g.setColor(Color.LIGHT_GRAY);
                        g.setColor(new Color(230, 230, 230, 100));
                    } else {
                        g.setColor(Color.BLACK);
                        //g.setColor(colors[index]);

                    }

                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setStroke(new BasicStroke(4));

                    Polygon side = grid.getSide(cell, index, size);
                    side.translate(px, py);
                    g.drawPolygon(side);
                }


                //if (x % 2 == 0) py += size / 2;


                //if (y % 2 == 0) py += size / 2;
/*
                float ratio = (float) cell.depth / (float) getGrid().maxDepth;

                float[] end = new float[3];
                for (int i = 0; i < 3; i++) {
                    end[i] = Lerp.rainbow[i].interpolate(ratio);
                }

                Color c = new Color(end[0], end[1], end[2], 150f / 255f);

                g.setColor(c);*/


            /*    if (walkable) {
                    g.setColor(Color.WHITE);
                    g.drawRect(px, py, size - 1, size - 1);
                    //g.setColor(Color.LIGHT_GRAY);
                    //g.drawRect(px, py, size, size);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawRect(px, py, size - 1, size - 1);
                    //g.setColor(Color.LIGHT_GRAY);
                    //g.drawRect(px, py, size - 1, size - 1);
                }*/


            }
        }

    }



}
