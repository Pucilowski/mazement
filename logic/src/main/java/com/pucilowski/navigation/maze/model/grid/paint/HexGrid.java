package com.pucilowski.navigation.maze.model.grid.paint;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 27/01/12
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public class HexGrid extends GridRenderer{
    int side;
    int across;
    int down;

    public HexGrid(int side, int across, int down) {
        this.side = side;
        this.across = across;
        this.down = down;
    }

    public int getWidth() {
        return across * side + (across + 1) * (side / 2);
    }

    public int getHeight() {
        int h = (int) (Math.sin(Math.toRadians(60)) * (float) side);

        return down * h * 2 + h;
    }

    public float getTriWidth() {
        return side / 2;
    }

    public float getTriHeight() {
        return (float) (Math.sin(Math.toRadians(60)) * (float) side);
    }

    public Polygon getPolygon(int x, int y) {
        int x_offset = side / 2;
        int y_offset = (int) (Math.sin(Math.toRadians(60)) * (float) side);
        int extra_y = 0;
        if (x % 2 == 0) {
            extra_y = y_offset;
        }

        int left_x = x * side + x * side / 2;
        int right_x = left_x + 2 * side;

        int mid_left_x = left_x + x_offset;
        int mid_right_x = right_x - x_offset;

        int top_y = extra_y + (y * y_offset * 2);
        int mid_y = top_y + y_offset;
        int bottom_y = mid_y + y_offset;

        return new Polygon(
                new int[]{mid_left_x, mid_right_x, right_x, mid_right_x, mid_left_x, left_x},
                new int[]{top_y, top_y, mid_y, bottom_y, bottom_y, mid_y},
                6
        );
    }

    public Point getPolygonCenter(int x, int y) {
        Polygon p = getPolygon(x, y);

        int p_x = (p.xpoints[5] + p.xpoints[2]) / 2;
        int p_y = p.ypoints[2];

        return new Point(p_x, p_y);
    }

/*    public void paint(Graphics g, Point p, HexTile tile) {

        for (int x = 0; x < across; x++) {
            for (int y = 0; y < down; y++) {

                Polygon hex = getPolygon(x, y);
                hex.translate(p.x, p.y);

                tile.paintHex(g, hex, x, y);
            }
        }

    }*/
}

