package com.pucilowski.navigation.ui.business;

import com.pucilowski.navigation.Main;
import com.pucilowski.navigation.ui.events.Event;
import com.pucilowski.navigation.ui.events.GenerateMazeEvent;
import com.pucilowski.navigation.ui.events.NewGridEvent;
import com.pucilowski.navigation.ui.events.SolveMazeEvent;

import java.util.LinkedList;

/**
 * Created by martin on 24/12/13.
 */
public class EventHandler implements Runnable {

    public final Main main;

    LinkedList<Event> events = new LinkedList<Event>();


    public EventHandler(Main main) {
        this.main = main;


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

            main.grid = event.type.newMaze(event.width, event.height);
            main.algo = null;

        }

        if (e instanceof GenerateMazeEvent) {
            GenerateMazeEvent event = (GenerateMazeEvent) e;

            main.grid.reset();

            main.algo = event.gen.newGenerator(main.grid);

            main.algo.start();

        }

        if (e instanceof SolveMazeEvent) {
            SolveMazeEvent event = (SolveMazeEvent) e;

            main.algo = event.gen.newPathfinder(main.grid);

            main.algo.start();

        }

        main.gui.frame.repaint();
    }
}
