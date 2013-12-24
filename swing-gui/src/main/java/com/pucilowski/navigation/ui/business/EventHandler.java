package com.pucilowski.navigation.ui.business;

import com.pucilowski.navigation.Mazing;
import com.pucilowski.navigation.ui.events.Event;
import com.pucilowski.navigation.ui.events.GenerateMazeEvent;
import com.pucilowski.navigation.ui.events.NewGridEvent;
import com.pucilowski.navigation.ui.events.SolveMazeEvent;

import java.util.LinkedList;

/**
 * Created by martin on 24/12/13.
 */
public class EventHandler implements Runnable {

    public final Mazing mazing;

    LinkedList<Event> events = new LinkedList<Event>();


    public EventHandler(Mazing mazing) {
        this.mazing = mazing;



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
            System.out.println("Processing: "+ e);


            if(e instanceof NewGridEvent) {
                NewGridEvent event= (NewGridEvent) e;

                mazing.grid = event.type.newMaze(event.width,event.height);
                mazing.algo = null;

            }

            if(e instanceof GenerateMazeEvent) {
                GenerateMazeEvent event = (GenerateMazeEvent) e;

                mazing.algo = event.gen.newGenerator(mazing.grid);

                mazing.algo.start();

            }

            if(e instanceof SolveMazeEvent) {
                SolveMazeEvent event = (SolveMazeEvent) e;

                mazing.algo = event.gen.newPathfinder(mazing.grid);

                mazing.algo.start();

            }

            mazing.gui.frame.repaint();

        }


    }


    public void sendEvent(Event event) {
        System.out.println("Event posted");
        events.add(event);
    }
}
