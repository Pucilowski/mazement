package com.pucilowski.navigation.ui.swing.panels;

import com.pucilowski.navigation.Main;
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

    final Main main;


    int xOffset;
    int yOffset;
    int cellSize;

    public GridPanel(Main main) {
        this.main = main;

        resize();
    }

    Rectangle r;

    public void resize() {
        Grid grid = main.grid;

        grid.resize(getBounds());

       /* PointD d = grid.getScaledSize();
        double sWidth = d.x;
        double sHeight = d.y;

        cellSize = (int) Math.floor(Math.min((double) getWidth() / sWidth, (double) getHeight() / sHeight));

        grid.resize(cellSize);*/

        Point d2 = grid.getSize();

        int width = d2.x;
        int height = d2.y;

        //xOffset = (int) ((getWidth() - cellSize * w) / 2);
        //yOffset = (int) ((getHeight() - cellSize * h) / 2);
        xOffset = (int) ((getWidth() - width) / 2D);
        yOffset = (int) ((getHeight() - height) / 2D);

        r = new Rectangle(xOffset, yOffset, width, height);

        int w2 = (int) (cellSize * width);
        int h2 = (int) (cellSize * height);

        System.out.println(w2 + ", " + h2);


        //main.gui.frame.invalidate();

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

        resize();


        Grid grid = main.grid;
        Algorithm algo = main.algo;

        //resize();


        //int size = 24;

        g.setColor(Color.RED);
        ((Graphics2D) g).draw(r);

        for (int y = 0; y < grid.height; y++) {
            for (int x = 0; x < grid.width; x++) {
                //if(x%2!=1 )continue;

                Cell cell = grid.cells[x][y];

                //Point location = grid.getLocation(cell, size);
                Point location = grid.getLocation(cell);
                int px = xOffset + location.x;
                int py = yOffset + location.y;

                //Polygon polygon = grid.getPolygon(cell, size);
                Polygon polygon = grid.getPolygon(cell);
                polygon.translate(px, py);


                if (algo != null) {
                    Color c = algo.getColor(cell);

                    if (c != null) {
                        g.setColor(c);
                        //px = 8 + size/2 * x;
                        //py = 8 + size * y;
                        g.fillPolygon(polygon);
                    }
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


                    Polygon side = grid.getSide(cell, index);
                    side.translate(px, py);
                    g.drawPolygon(side);
                }


            }
        }


        if (algo != null) {

            for (int y = 0; y < grid.height; y++) {
                for (int x = 0; x < grid.width; x++) {
                    //if(x%2!=1 )continue;

                    Cell cell = grid.cells[x][y];

                    //Point location = grid.getLocation(cell, size);
                    Point location = grid.getLocation(cell);
                    int px = xOffset + location.x;
                    int py = yOffset + location.y;

                    //Polygon polygon = grid.getPolygon(cell, size);
                    Polygon polygon = grid.getPolygon(cell);
                    polygon.translate(px, py);


                    if (cell.equals(algo.getCurrent())) {

                        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));


                        g.setColor(Color.BLACK);

                        g.drawPolygon(polygon);

                    }

                }
            }
        }

    }


}
