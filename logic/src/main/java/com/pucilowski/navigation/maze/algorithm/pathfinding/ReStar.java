package com.pucilowski.navigation.maze.algorithm.pathfinding;

import com.pucilowski.navigation.maze.algorithm.CellMeta;
import com.pucilowski.navigation.maze.algorithm.Pathfinder;
import com.pucilowski.navigation.maze.algorithm.State;
import com.pucilowski.navigation.maze.grid.Cell;
import com.pucilowski.navigation.maze.grid.Edge;
import com.pucilowski.navigation.maze.grid.Grid;
import com.pucilowski.navigation.maze.grid.paint.Lerp;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by martin on 11/12/13.
 */
public class ReStar extends Pathfinder<SearchMeta> {

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



    Distance weights;

    public int maxOrder = 0;
    public int maxDepth = 1;


    public ReStar(Grid grid, Cell start, Cell goal, Distance distance) {
        super(grid, start, goal);

        this.weights = distance;

        start();
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
    public void step() {
        //System.out.println("############ step(" + start + ", " + finish + ")");

        if (open.isEmpty()) {
            state = (State.FAILED);
            return;
        }

        Cell current = open.peek();
        SearchMeta currentMeta = getMeta(current);
        maxOrder++;
        currentMeta.order = maxOrder;

        if (current.equals(goal) && false) {
            state = State.SUCCESS;
            path = path(current);
            return;
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

  /*      if (true) {
            for (Edge e : cell.getEdges()) {
                SearchMeta sm = getMeta(e.target);
                if (cell.equals(sm.parent)) {
                    return colors[e.index];
                }
            }
        }*/

        int d = getMeta(cell).depth;
//        int d = getMeta(cell).order;

        if (closed.contains(cell)) {

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
        } else if (open.contains(cell)) {
            Color c = new Color(192, 192, 192, 255);

            return c;

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
