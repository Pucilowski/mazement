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

    public static final  Color[] colors = {
            Color.RED,
            Color.GREEN,
            Color.YELLOW,
            Color.BLUE,
            Color.MAGENTA,
            Color.CYAN
    };

    @Override
    public void paintComponent(Graphics g) {
        Grid grid = getGrid();


        int w = grid.width;
        int h = grid.height;


        int size = Math.min(getWidth() / w, getHeight() / h);
        //int size = 24;



        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                //if(x%2!=1 )continue;

                Cell cell = grid.cells[x][y];

                Point location = grid.getLocation(cell, size);
                //Polygon polygon = grid.getPolygon(cell, size);

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


            }
        }

    }



}
