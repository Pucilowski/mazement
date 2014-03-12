package com.pucilowski.navigation.mazes.process.pathfinding;

import com.pucilowski.navigation.mazes.process.CellMeta;
import com.pucilowski.navigation.mazes.process.Pathfinder;
import com.pucilowski.navigation.mazes.process.State;
import com.pucilowski.navigation.mazes.grid.Cell;
import com.pucilowski.navigation.mazes.grid.Edge;
import com.pucilowski.navigation.mazes.grid.Grid;
import com.pucilowski.navigation.mazes.grid.misc.Lerp;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by martin on 19/12/13.
 */
public class DFSearch extends Pathfinder<SearchMeta> {

    ArrayList<Cell> explored = new ArrayList<Cell>();
    LinkedList<Cell> visited = new LinkedList<Cell>();

    int maxDepth;

    public DFSearch(Grid grid, Cell start, Cell goal) {
        super(grid, start, goal);
    }

    public DFSearch(Grid grid) {
        this(grid, null, null);
    }

    @Override
    public void start() {
        explored.add(start);
        visited.push(start);
    }

    @Override
    public boolean step() {

        if (visited.isEmpty()) {
            state = State.FAILED;
            return false;
        }

        Cell current = visited.peek();

        if (current.equals(goal)) {
            state = State.SUCCESS;
            path = path(current);
            return false;
        }

        Edge[] edges = current.getEdges();

        CellMeta cM = getMeta(current);

        for (Edge edge : edges) {
            if (!grid.isConnected(edge) || explored.contains(edge.target)) continue;
            //System.out.println("Neighbor: " + edge.target);
            Cell neighbor = edge.target;
            SearchMeta neighborMeta = getMeta(neighbor);


            neighborMeta.parent = current;
            neighborMeta.depth = cM.depth + 1;
            if (neighborMeta.depth > maxDepth) maxDepth = neighborMeta.depth;

            visited.push(edge.target);
            explored.add(edge.target);

            return true;
        }

        visited.pop();
        return true;
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

        if (explored.contains(cell)) {
            if (cell.walls == 0) return null;
            float ratio = (float) d / (float) maxDepth;

            float[] end = new float[3];
            for (int i = 0; i < 3; i++) {
                end[i] = Lerp.rainbow[i].interpolate(ratio);
            }

            return new Color(end[0], end[1], end[2], 150f / 255f);
        }
        return null;


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
