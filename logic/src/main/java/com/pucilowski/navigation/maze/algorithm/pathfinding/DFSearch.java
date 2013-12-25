package com.pucilowski.navigation.maze.algorithm.pathfinding;

import com.pucilowski.navigation.maze.algorithm.CellMeta;
import com.pucilowski.navigation.maze.algorithm.Pathfinder;
import com.pucilowski.navigation.maze.algorithm.State;
import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Edge;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.misc.Lerp;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by martin on 19/12/13.
 */
public class DFSearch extends Pathfinder<SearchMeta> {

    LinkedList<Cell> visited = new LinkedList<Cell>();

    int maxDepth;

    public DFSearch(Grid grid, Cell start, Cell goal) {
        super(grid, start, goal);
    }

    @Override
    public void start() {
        visited.push(grid.cells[0][0]);
    }

    @Override
    public void step() {

        if (visited.isEmpty()) {
            state = State.FAILED;
            return;
        }

        Cell current = visited.peek();


        if (current.equals(goal)) {
            state = State.SUCCESS;
            path = path(current);
            return;
        }

        Edge[] edges = current.getEdges();
        //Collections.shuffle(Arrays.asList(edges), random);

        CellMeta cM = getMeta(current);

        for (Edge edge : edges) {
            if (!grid.isConnected(edge)) continue;
            //System.out.println("Neighbor: " + edge.target);
            Cell neighbor = edge.target;
            SearchMeta neighborMeta = getMeta(neighbor);


            neighborMeta.parent = current;
            neighborMeta.depth = cM.depth + 1;
            if (neighborMeta.depth > maxDepth) maxDepth = neighborMeta.depth;

            visited.push(edge.target);


            return;

        }

        visited.pop();
    }

    public Cell[] path(Cell v) {
        ArrayList<Cell> path = new ArrayList<Cell>();

        do {
            path.add(0, v);
        } while ((v = getMeta(v).parent) != null);

        return path.toArray(new Cell[path.size()]);
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
