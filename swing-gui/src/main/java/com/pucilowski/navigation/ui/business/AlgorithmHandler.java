package com.pucilowski.navigation.ui.business;

import com.pucilowski.navigation.Main;
import com.pucilowski.navigation.maze.algorithm.State;

/**
 * Created by martin on 24/12/13.
 */
public class AlgorithmHandler implements Runnable {

    final Main main;

    public AlgorithmHandler(Main main) {
        this.main = main;

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while (true) {
            if (main.algo != null && main.algo.state == State.WORKING) {
                //System.out.println("step");

                main.algo.step();
                main.gui.frame.repaint();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
