package com.pucilowski.navigation.mazes.process.pathfinding;

import com.pucilowski.navigation.mazes.process.Pathfinder;
import com.pucilowski.navigation.mazes.process.State;
import com.pucilowski.navigation.mazes.grid.Cell;
import com.pucilowski.navigation.mazes.grid.Edge;
import com.pucilowski.navigation.mazes.grid.Grid;
import com.pucilowski.navigation.mazes.grid.misc.Lerp;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by martin on 11/12/13.
 */
public class AStar extends Pathfinder<SearchMeta> {

    public final ArrayList<Cell> closed = new ArrayList<Cell>();
    public final PriorityQueue<Cell> open = new PriorityQueue<Cell>(1, new Comparator<Cell>() {
        @Override
        public int compare(Cell o1, Cell o2) {
            // cheaper nodes first
            SearchMeta m1 = getMeta(o1);
            SearchMeta m2 = getMeta(o2);

            double d = m1.fScore - m2.fScore;

            return (int) Math.signum(d);
        }
    });


    Distance weights = new Distance() {
        @Override
        public double weight(Cell a, Cell b) {
            return 1;
        }

        @Override
        public double heuristic(Cell a, Cell b) {
            return a.manhattan(b);
        }
    };

    public int maxOrder = 0;
    public int maxDepth = 1;


    public AStar(Grid grid, Cell start, Cell goal) {
        super(grid, start, goal);
    }

    public AStar(Grid grid) {
        this(grid, null, null);
    }


    @Override
    public void start() {
        open.add(start);

        SearchMeta startMeta = getMeta(start);

        //start.g_score = 0D;
        startMeta.gScore = 0D;
        //start.f_score = start.g_score + weights.heuristic(start, goal);
        startMeta.fScore = startMeta.gScore + weights.heuristic(start, goal);
    }

    @Override
    public boolean step() {
        System.out.println("############ step(" + start + ", " + goal + ")");

        if (open.isEmpty()) {
            state = (State.FAILED);
            return false;
        }

        Cell current = open.peek();
        SearchMeta currentMeta = getMeta(current);
        maxOrder++;
        currentMeta.order = maxOrder;

        if (current.equals(goal)) {
            state = State.SUCCESS;
            path = path(current);
            return false;
        }

        open.remove(current);
        closed.add(current);

        for (Edge edge : current.getEdges()) {
            if (!grid.isConnected(edge)) continue;

            Cell neighbor = edge.target;
            SearchMeta neighborMeta = getMeta(neighbor);

            double tentative_g_score = currentMeta.gScore + weights.weight(current, neighbor);
            double tentative_f_score = tentative_g_score + weights.heuristic(neighbor, goal);

            if (closed.contains(neighbor) && tentative_f_score >= neighborMeta.fScore)
                continue;

            if (!open.contains(neighbor) || tentative_f_score < neighborMeta.fScore) {
                neighborMeta.parent = current;
                neighborMeta.depth = currentMeta.depth + 1;
                if (neighborMeta.depth > maxDepth) maxDepth = neighborMeta.depth;

                neighborMeta.gScore = tentative_g_score;
                neighborMeta.fScore = tentative_f_score;
                if (!open.contains(neighbor)) {
                    open.add(neighbor);
                }
            }
        }

        return true;
    }

    public Cell[] path(Cell v) {
        ArrayList<Cell> path = new ArrayList<Cell>();

        do {
            path.add(0, v);
        } while ((v = getMeta(v).parent) != null);

        return path.toArray(new Cell[path.size()]);
    }

    public Color[] colors = {
            Color.RED,
            Color.GREEN,
            Color.YELLOW,
            Color.BLUE
    };

    @Override
    public Color getColor(Cell cell) {

        int d = getMeta(cell).depth;

        if (closed.contains(cell)) {
            float ratio = (float) d / (float) maxDepth;

            float[] end = new float[3];
            for (int i = 0; i < 3; i++) {
                end[i] = Lerp.rainbow[i].interpolate(ratio);
            }

            return new Color(end[0], end[1], end[2], 150f / 255f);
        } else if (open.contains(cell)) {

            return new Color(192, 192, 192, 255);

            //g.setColor(c);
            //px = 8 + size/2 * x;
            //py = 8 + size * y;
            //polygon.translate(px, py);
            //g.fillPolygon(polygon);
        }

        return null;
    }

    @Override
    public Cell getCurrent() {
        return open.peek();
    }
}
