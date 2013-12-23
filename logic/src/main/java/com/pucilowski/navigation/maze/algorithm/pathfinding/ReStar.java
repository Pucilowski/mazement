package com.pucilowski.navigation.maze.algorithm.pathfinding;

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


    public Cell[] path = null;

    Distance weights;

    public int maxDepth = 1;

    public ReStar(Grid grid, Cell start, Cell goal, Distance distance) {
        super(grid, start, goal);

        this.weights = distance;

        start();
    }


    @Override
    public SearchMeta newMeta(Cell cell) {
        return new SearchMeta(cell);
    }


    @Override
    public void start() {
        open.add(start);


        //start.g_score = 0D;
        getMeta(start).gScore = 0D;
        //start.f_score = start.g_score + weights.heuristic(start, finish);
        getMeta(start).fScore = 0D;
    }

    @Override
    public void step() {
        //System.out.println("############ step(" + start + ", " + finish + ")");

        if (open.isEmpty()) {
            state = (State.FAILED);
            return;
        }

        Cell current = open.peek();

        if (current.equals(goal)) {
            state = State.SUCCESS;
            path = path(current);
            return;
        }

        open.remove(current);
        closed.add(current);

        for (Edge edge : current.getEdges()) {
            if (!grid.isConnected(edge)) continue;
            Cell neighbor = edge.target;

            SearchMeta mCurrent = getMeta(current);
            SearchMeta mNeighbor = getMeta(neighbor);

            double tentative_g_score = mCurrent.gScore + weights.weight(current, neighbor);
            double tentative_f_score = tentative_g_score + weights.heuristic(neighbor, goal);

            if (closed.contains(neighbor) && tentative_f_score >= mNeighbor.fScore)
                continue;

            if (!open.contains(neighbor) || tentative_f_score < mNeighbor.fScore) {
                mNeighbor.parent = current;
                mNeighbor.depth = mCurrent.depth + 1;
                if (mNeighbor.depth > maxDepth) maxDepth = mNeighbor.depth;

                mNeighbor.gScore = tentative_g_score;
                mNeighbor.fScore = tentative_f_score;
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

    @Override
  public  Color getColor(Cell cell) {

        int d = getMeta(cell).depth;

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
            Color c = new Color(192,192,192,255);

            return c;

            //g.setColor(c);
            //px = 8 + size/2 * x;
            //py = 8 + size * y;
            //polygon.translate(px, py);
            //g.fillPolygon(polygon);
        }

        return null;
    }
}
