package com.pucilowski.navigation.environment.business;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.mazes.process.*;
import com.pucilowski.navigation.mazes.process.Task;

/**
 * Created by martin on 24/12/13.
 */
public class TaskHandler implements Runnable {

    final Environment env;

    private Task task;

    public TaskHandler(Environment env) {
        this.env = env;

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        while (true) {

            if (task == null) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            boolean sleep = false;

            if (task.state == State.WORKING) {
                //System.out.println("step");

                if (task.step()) {
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

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
