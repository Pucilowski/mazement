package com.pucilowski.navigation.ui.events;

import com.pucilowski.navigation.Mazing;

import java.util.LinkedList;

/**
 * Created by martin on 24/12/13.
 */
public class EventHandler implements Runnable {

    public final Mazing mazing;

    LinkedList<Event> events = new LinkedList<Event>();


    public EventHandler(Mazing mazing) {
        this.mazing = mazing;
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

            }

            Event e = events.peek();

            if(e instanceof NewMazeEvent) {
                NewMazeEvent nme = (NewMazeEvent) e;


            }

        }

    }


    public void sendEvent(Event event) {
        System.out.println("Event posted");
        events.add(event);
    }
}
