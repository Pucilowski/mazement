package com.pucilowski.navigation.ui.business;

import com.pucilowski.navigation.Mazing;
import com.pucilowski.navigation.maze.algorithm.State;

/**
 * Created by martin on 24/12/13.
 */
public class AlgorithmHandler implements Runnable {

    final Mazing mazing;

    public AlgorithmHandler(Mazing mazing) {
        this.mazing = mazing;

        Thread t= new Thread(this);
        t.start();
    }

    @Override
    public void run() {


        while (true) {

            if(mazing.algo==null) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            while (mazing.algo.state == State.WORKING) {
                mazing.algo.step();


                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mazing.gui.frame.repaint();

            }

        }

    }
}
