package com.pucilowski.navigation.environment.business;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.mazes.process.State;

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

            if (env.process == null) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            boolean sleep = false;

            if (env.process.state == State.WORKING) {
                //System.out.println("step");

                if (env.process.step()) {
                    sleep = true;
                }
                env.onRefresh();

                if (sleep ) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



        }
    }
}
