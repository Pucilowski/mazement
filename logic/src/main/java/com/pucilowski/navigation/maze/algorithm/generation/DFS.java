package com.pucilowski.navigation.maze.algorithm.generation;

import com.pucilowski.navigation.maze.algorithm.CellMeta;
import com.pucilowski.navigation.maze.algorithm.Generator;
import com.pucilowski.navigation.maze.algorithm.State;
import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Edge;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.paint.Lerp;

import java.awt.*;
import java.util.*;

/**
 * Created by martin on 19/12/13.
 */
public class DFS extends Generator {

    LinkedList<Cell> visited = new LinkedList<Cell>();

    int maxDepth;

    public DFS(Grid grid) {
        super(grid);
    }

    @Override
    public void start() {
        visited.push(grid.cells[0][0]);
    }

    @Override
    public void step() {
        if (visited.isEmpty()) {
            System.out.println("|");
            state = State.SUCCESS;
            return;
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

                step.onStep(edge.target);

                return;
            }
        }

        visited.pop();
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

    @Override
    public Cell getCurrent() {
        return visited.peek();
    }
}
