package com.pucilowski.navigation.environment.business;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.logic.process.State;

/**
 * Created by martin on 24/12/13.
 */
public class ProcessHandler implements Runnable {

    final Environment env;

    public ProcessHandler(Environment env) {
        this.env = env;

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while (true) {
            if (env.process != null && env.process.state == State.WORKING) {
                //System.out.println("step");

                env.process.step();
                env.onRefresh();




            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}