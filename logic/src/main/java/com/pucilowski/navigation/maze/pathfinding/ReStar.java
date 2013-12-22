package com.pucilowski.navigation.maze.pathfinding;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Edge;
import com.pucilowski.navigation.maze.model.grid.Grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by martin on 11/12/13.
 */
public class ReStar extends Pathfinder<SearchMeta> {

    final ArrayList<Cell> closed = new ArrayList<Cell>();
    final PriorityQueue<Cell> open = new PriorityQueue<Cell>(1, new Comparator<Cell>() {
        @Override
        public int compare(Cell o1, Cell o2) {
            // cheaper nodes first



            //SearchMeta m1 = getMeta(o1);
            //SearchMeta m2 = getMeta(o2);

            //double d = o1.f_score - o2.f_score;

            //return (int) Math.signum(d);

            return 0;
        }
    });

    final HashMap<Cell, Double> fScores = new HashMap<Cell, Double>();
    final HashMap<Cell, Double> gScores = new HashMap<Cell, Double>();

    Cell[] path = null;

    public ReStar(Grid grid, Cell start, Cell goal) {
        super(grid, start, goal);


        init();
    }

    public void init() {
        open.add(start);

        //start.g_score = 0D;
        gScores.put(start, 0D);
        //start.f_score = start.g_score + weights.heuristic(start, finish);
        fScores.put(start, 0D);
    }

    @Override
    public void start() {

    }

    @Override
    public void step() {
        //System.out.println("############ step(" + start + ", " + finish + ")");

        if (!open.isEmpty()) {
            Cell current = open.peek();

            if (current.equals(goal)) {
                //setState(State.SUCCESS);
                //path = path(current);
                return;
            }

            open.remove(current);
            closed.add(current);

            for (Edge edge : current.getEdges()) {

            }

            return;
        }

        //setState(State.FAILED);
    }



}
