package com.pucilowski.navigation.maze.model.grid;

import com.pucilowski.navigation.maze.model.Cell;
import com.pucilowski.navigation.maze.model.Neighborship;

import java.awt.*;

/**
 * Created by martin on 10/12/13.
 */
public abstract class Grid {

    public final int width;
    public final int height;
    public final int sides;
    public Cell[][] cells;

    public BinaryGrid binaryGrid = new BinaryGrid(this);
    public int maxDepth = 1;


    public Grid(int width, int height, int sides) {
        this.width = width;
        this.height = height;
        this.sides = sides;
        cells = new Cell[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = new Cell(x, y, sides);
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                Cell cell = cells[x][y];
                //ArrayList<Neighborship> neighborList = new ArrayList<Neighborship>();


                for (int index = 0; index < sides; index++) {


                    Cell neighbor = getAdjacentTile(cell, index);
                    if (neighbor == null) continue;
                    //cell.neighborships[s] = opp;
                    //cell.neighborships.put(index, neighbor);

                    //cell.neighborships[index] = new Neighborship(index, neighbor);
                    cell.neighborships[index] = new Neighborship(cell, neighbor, index);
                }

            }
        }

    }

    public boolean contains(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public void gen() {
        binaryGrid = new BinaryGrid(this);
    }

    public boolean isConnected(Neighborship n) {
        int index = n.index;

        int cflag = (int) Math.pow(2, index);
        int opp = (index + (sides / 2)) % sides;
        int nflag = (int) Math.pow(2, opp);

        //System.out.println("Current: " + n.source + " Adjacent: " + n.target + " c: " + cflag + " nflag: " + nflag);

        return (n.source.walls & cflag) == cflag && (n.target.walls & nflag) == nflag;

    }

    public void connect(Neighborship n) {

        int index = n.index;

        int cflag = (int) Math.pow(2, index);
        int opp = (index + (sides / 2)) % sides;
        int nflag = (int) Math.pow(2, opp);

        //System.out.println("Current: " + n.source + " Adjacent: " + n.target + " c: " + cflag + " nflag: " + nflag);

        n.source.walls |= cflag;
        n.target.walls |= nflag;

        //cells[cx][cy].walls |= cflag;
        //cells[nx][ny].walls |= nflag;

    }

    public abstract Point getOffset(Cell cell, int index);

    public abstract Cell getAdjacentTile(Cell current, int index);

    public abstract Cell[] getAdjacentTiles(Cell cell);


    public abstract void display();


}
