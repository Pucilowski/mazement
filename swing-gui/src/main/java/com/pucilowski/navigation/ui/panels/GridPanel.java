package com.pucilowski.navigation.ui.panels;

import com.pucilowski.navigation.Mazing;
import com.pucilowski.navigation.maze.algorithm.Algorithm;
import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Edge;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.paint.PointD;

import javax.swing.*;
import java.awt.*;

/**
 * Created by martin on 11/12/13.
 */
public class GridPanel extends JPanel {
    Mazing mazing;

    public GridPanel(Mazing mazing) {
        this.mazing = mazing;
    }


    public static final Color[] colors = {
            Color.RED,
            Color.GREEN,
            Color.YELLOW,
            Color.BLUE,
            Color.MAGENTA,
            Color.CYAN
    };

    @Override
    public void paintComponent(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;

        Grid grid = mazing.grid;
        Algorithm algo = mazing.algo;


        PointD d = grid.getSize();
        double w = d.x;
        double h = d.y;


        int size = (int) Math.floor( Math.min((double)getWidth() / w, (double)getHeight() / h));
        //int size = 24;


        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                //if(x%2!=1 )continue;

                Cell cell = grid.cells[x][y];

                Point location = grid.getLocation(cell, size);
                int px = 8 + location.x;
                int py = 12 + location.y;

                Polygon polygon = grid.getPolygon(cell, size);
                polygon.translate(px, py);




                Color c = algo.getColor(cell);

                if (c != null) {
                    g.setColor(c);
                    //px = 8 + size/2 * x;
                    //py = 8 + size * y;
                    g.fillPolygon(polygon);
                }



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
                        continue;
                    } else {
                        g.setColor(Color.BLACK);
                        //g.setColor(colors[index]);

                    }

                    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));


                    Polygon side = grid.getSide(cell, index, size);
                    side.translate(px, py);
                    g.drawPolygon(side);
                }




            }
        }



        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                //if(x%2!=1 )continue;

                Cell cell = grid.cells[x][y];

                Point location = grid.getLocation(cell, size);
                int px = 8 + location.x;
                int py = 12 + location.y;

                Polygon polygon = grid.getPolygon(cell, size);
                polygon.translate(px, py);



                if(cell.equals(algo.getCurrent())) {

                    g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));


                    g.setColor(Color.BLACK);

                    g.drawPolygon(polygon);

                }

            }
        }

    }


}
