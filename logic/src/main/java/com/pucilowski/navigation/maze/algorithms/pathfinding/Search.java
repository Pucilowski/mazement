package com.pucilowski.navigation.maze.algorithms.pathfinding;

import com.pucilowski.navigation.maze.algorithms.pathfinding.model.Graph;
import com.pucilowski.navigation.maze.algorithms.pathfinding.model.Vertex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
* Created by martin on 11/12/13.
*/
public class Search {

    final Graph graph;
    final Distance weights;
    final Vertex start;
    final Vertex finish;


    final ArrayList<Vertex> closed = new ArrayList<Vertex>();
    final PriorityQueue<Vertex> open = new PriorityQueue<Vertex>(1, new Comparator<Vertex>() {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            // cheaper nodes first
            double d = o1.f_score - o2.f_score;
            return (int) Math.signum(d);
        }
    });


    // result
    State state = State.WORKING;
    Vertex[] path = null;


    enum State {
        WORKING,
        FAILED,
        SUCCESS
    }

    public void setState(State state) {
        this.state = state;
        System.out.println("[SEARCH] State: " + state);
    }


    public Search(Graph graph, Distance weights, Vertex start, Vertex finish) {
        this.graph = graph;
        this.weights = weights;
        this.start = start;
        this.finish = finish;
        //this.iSearch = iSearch;

        init();
    }

    public void init() {
        open.add(start);

        start.g_score = 0D;
        start.f_score = start.g_score + weights.heuristic(start, finish);

        //iSearch.onInit(start, finish);
    }

    public void step() {
        //System.out.println("############ step(" + start + ", " + finish + ")");

        if (!open.isEmpty()) {
            //GrandFinale.GrandFinale.Search.Graph.Vertex current = getCheapestOpen();
            Vertex current = open.peek();

            //iSearch.onStep(current);



            if (current.equals(finish)) {
                setState(State.SUCCESS);
                path = path(current);
                return;
            }

            open.remove(current);
            closed.add(current);

            for (Vertex neighbor : graph.getValencies(current)) {
                //System.out.println("Valency: " + neighbor);

                double tentative_g_score = current.g_score + weights.weight(current, neighbor);
                double tentative_f_score = tentative_g_score + weights.heuristic(neighbor, finish);

                if (closed.contains(neighbor) && tentative_f_score >= neighbor.f_score)
                    continue;

                if (!open.contains(neighbor) || tentative_f_score < neighbor.f_score) {
                    neighbor.parent = current;
                    neighbor.depth = current.depth + 1;

                    neighbor.g_score = tentative_g_score;
                    neighbor.f_score = tentative_f_score;
                    if (!open.contains(neighbor)) {
                        open.add(neighbor);
                    }
                }

                //iSearch.onNeighbor(neighbor);
            }

            return;
        }

        setState(State.FAILED);
    }

    public Vertex[] findPath() {
        while (state == State.WORKING) {
            step();
        }

        return path;
    }

    public Vertex[] retrace(Vertex start, Vertex goal) {
        Vertex[] a = path(start);
        Vertex[] b = path(goal);

        Vertex common = null;
        int depth = -1;

        for (Vertex v1 : a) {
            for (Vertex v2 : b) {
                if (v1.equals(v2) && v1.depth > depth) {
                    common = v1;
                    break;
                }
            }
        }

        LinkedList<Vertex> path = new LinkedList<Vertex>();

        for (int i = a.length - 1; i >= 0; i--) {
            Vertex v = a[i];
            if (v.equals(common)) break;
            path.add(v);
        }

        boolean s = false;
        for (Vertex v : b) {
            if (v.equals(common)) {
                s = true;
            }

            if (!s) continue;

            path.add(v);
        }

        path.pop();

        return path.toArray(new Vertex[path.size()]);
    }

    public Vertex[] path(Vertex v) {
        ArrayList<Vertex> path = new ArrayList<Vertex>();

        do {
            path.add(0, v);
        } while ((v = v.parent) != null);

        return path.toArray(new Vertex[path.size()]);
    }

}
