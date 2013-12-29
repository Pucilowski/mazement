package com.pucilowski.navigation.environment.business;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.logic.algorithm.State;

/**
 * Created by martin on 24/12/13.
 */
public class AlgorithmHandler implements Runnable {

    final Environment env;

    public AlgorithmHandler(Environment env) {
        this.env = env;

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while (true) {
            if (env.algo != null && env.algo.state == State.WORKING) {
                //System.out.println("step");

                env.algo.step();
                env.onRefresh();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
