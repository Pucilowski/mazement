package com.pucilowski.navigation.environment.business;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.environment.events.Event;
import com.pucilowski.navigation.environment.events.GenerateMazeEvent;
import com.pucilowski.navigation.environment.events.NewGridEvent;
import com.pucilowski.navigation.environment.events.SolveMazeEvent;

import java.util.LinkedList;

/**
 * Created by martin on 24/12/13.
 */
public class EventHandler implements Runnable {

    public final Environment env;

    LinkedList<Event> events = new LinkedList<Event>();


    public EventHandler(Environment env) {
        this.env = env;


        Thread t = new Thread(this);
        t.start();
    }

    class T extends Thread {

    }


    @Override
    public void run() {

        while (true) {

            if (events.isEmpty()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                continue;
            }

            Event e = events.pop();
            System.out.println("Processing: " + e);


            handle(e);

        }


    }


    public void sendEvent(Event event) {
        System.out.println("Event posted");
        events.add(event);
    }

    private void handle(Event e) {

        if (e instanceof NewGridEvent) {
            NewGridEvent event = (NewGridEvent) e;

            env.grid = event.type.newMaze(event.width, event.height);
            env.algo = null;

        }

        if (e instanceof GenerateMazeEvent) {
            GenerateMazeEvent event = (GenerateMazeEvent) e;

            env.grid.reset();

            env.algo = event.gen.newGenerator(env.grid);

            env.algo.start();

        }

        if (e instanceof SolveMazeEvent) {
            SolveMazeEvent event = (SolveMazeEvent) e;

            env.algo = event.gen.newPathfinder(env.grid);

            env.algo.start();

        }

        env.onRefresh();
    }
}
