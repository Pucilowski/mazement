package com.pucilowski.navigation.maze.algorithm.generation;

import com.pucilowski.navigation.maze.algorithm.CellMeta;
import com.pucilowski.navigation.maze.algorithm.Generator;
import com.pucilowski.navigation.maze.algorithm.State;
import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Edge;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.paint.Lerp;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

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
    public void step() {

        if (walls.isEmpty()) {
            state = State.SUCCESS;
            return;
        }

        Collections.shuffle(walls, random);
        Edge wall = walls.get(0);

        //int n = random.nextInt(walls.size());
        //Edge wall = walls.get(n);


        if (visited.contains(wall.target)) {
            walls.remove(wall);
        } else {
            grid.connect(wall);

            CellMeta mSource = getMeta(wall.source);
            CellMeta mTarget = getMeta(wall.target);

            mTarget.depth = mSource.depth + 1;
            if (mTarget.depth > maxDepth) maxDepth = mTarget.depth;

            addCell(wall.target);
        }


        step.onStep(wall.source);


    }

    @Override
    public Color getColor(Cell cell) {

        int d = getMeta(cell).depth;


        if (cell.walls == 0) return null;
        float ratio = (float) d / (float) maxDepth;

        float[] end = new float[3];
        for (int i = 0; i < 3; i++) {
            end[i] = Lerp.rainbow[i].interpolate(ratio);
        }

        Color c = new Color(end[0], end[1], end[2], 150f / 255f);

        return c;

        //g.setColor(c);
        //px = 8 + size/2 * x;
        //py = 8 + size * y;
        //polygon.translate(px, py);
        //g.fillPolygon(polygon);

    }

}
