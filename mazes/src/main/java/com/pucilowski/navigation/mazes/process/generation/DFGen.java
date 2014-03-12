package com.pucilowski.navigation.mazes.process.generation;

import com.pucilowski.navigation.mazes.process.CellMeta;
import com.pucilowski.navigation.mazes.process.Generator;
import com.pucilowski.navigation.mazes.process.State;
import com.pucilowski.navigation.mazes.grid.Cell;
import com.pucilowski.navigation.mazes.grid.Edge;
import com.pucilowski.navigation.mazes.grid.Grid;
import com.pucilowski.navigation.mazes.grid.misc.Lerp;

import java.awt.*;
import java.util.*;

/**
 * Created by martin on 19/12/13.
 */
public class DFGen extends Generator {

    LinkedList<Cell> visited = new LinkedList<Cell>();

    int maxDepth;

    public DFGen(Grid grid) {
        super(grid);
    }

    @Override
    public void start() {
        visited.push(grid.cells[0][0]);
    }

    @Override
    public boolean step() {
        if (visited.isEmpty()) {
            System.out.println("|");
            state = State.SUCCESS;
            return false;
        }

        Cell current = visited.peek();

        Edge[] edges = current.getEdges();
        Collections.shuffle(Arrays.asList(edges), random);

        CellMeta cM = getMeta(current);

        for (Edge edge : edges) {
            if (edge == null) continue;

            //System.out.println("Neighbor: " + edge.target);

            if (edge.target.walls == 0) {

                CellMeta nM = getMeta(edge.target);
                nM.depth = cM.depth + 1;
                if (nM.depth > maxDepth) maxDepth = nM.depth;

                grid.connect(edge);
                visited.push(edge.target);

                return true;
            }
        }

        visited.pop();
        return true;
    }

    @Override
    public Color getColor(Cell cell) {

        int min = getMeta(cell).depth;
        //int max = maxDepth;
        int max = 20;

        min = min % max;
        if (min % (max * 2) >= max) {
            min = max - min;
        }

        if (cell.walls == 0) return null;
        float ratio = (float) min / (float) max;

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

    @Override
    public Cell getCurrent() {
        return visited.peek();
    }
}
