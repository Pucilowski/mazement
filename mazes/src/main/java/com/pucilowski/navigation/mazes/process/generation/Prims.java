package com.pucilowski.navigation.mazes.process.generation;

import com.pucilowski.navigation.mazes.process.CellMeta;
import com.pucilowski.navigation.mazes.process.Generator;
import com.pucilowski.navigation.mazes.process.State;
import com.pucilowski.navigation.mazes.grid.Cell;
import com.pucilowski.navigation.mazes.grid.Edge;
import com.pucilowski.navigation.mazes.grid.Grid;
import com.pucilowski.navigation.mazes.grid.misc.Lerp;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by martin on 19/12/13.
 */
public class Prims extends Generator {


    ArrayList<Cell> visited = new ArrayList<Cell>();
    ArrayList<Edge> walls = new ArrayList<Edge>();
    int maxDepth;

    final Cell start;

    public Prims(Grid grid, Cell start) {
        super(grid);
        this.start = start;
    }

    public Prims(Grid grid) {
        this(grid, grid.cells[grid.width/2][grid.height/2]);
    }

    public void start() {
        addCell(start);
    }

    public void addCell(Cell cell) {
        visited.add(cell);

        for (Edge n : cell.edges) {
            if (n == null) continue;
            walls.add(n);
        }
    }

    @Override
    public boolean step() {

        if (walls.isEmpty()) {
            state = State.SUCCESS;
            return false;
        }

        //Collections.shuffle(walls, random);
        //Edge wall = walls.get(0);

        int n = random.nextInt(walls.size());
        Edge wall = walls.get(n);


        if (visited.contains(wall.target)) {
            walls.remove(wall);

            return false;
        } else {
            grid.connect(wall);

            CellMeta mSource = getMeta(wall.source);
            CellMeta mTarget = getMeta(wall.target);

            mTarget.depth = mSource.depth + 1;
            if (mTarget.depth > maxDepth) maxDepth = mTarget.depth;

            addCell(wall.target);

            return true;
        }
    }


    @Override
    public Color getColor(Cell cell) {

        int min = getMeta(cell).depth;
        int max = maxDepth;
        /*int max = 40;

        min = min % max;
        if (min % (max * 2) >= max) {
            min = max - min;
        }*/

        if (cell.walls == 0) return null;
        float ratio = (float) min / (float) max;

        float[] end = new float[3];
        for (int i = 0; i < 3; i++) {
            end[i] = Lerp.rainbow[i].interpolate(ratio);
        }

        return new Color(end[0], end[1], end[2], 150f / 255f);

        //g.setColor(c);
        //px = 8 + size/2 * x;
        //py = 8 + size * y;
        //polygon.translate(px, py);
        //g.fillPolygon(polygon);

    }

}
