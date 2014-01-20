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

        e.handle(env);

        env.onRefresh();
    }
}
