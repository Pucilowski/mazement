package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.environment.enums.GridType;
import com.pucilowski.navigation.environment.events.NewGridEvent;

/**
 * Created by martin on 29/12/13.
 */
public class Main {

    Environment env;
    GUI gui;

    public Main() {
        env = new Environment() {
            @Override
            public void onRefresh() {
                gui.frame.repaint();
            }
        };

        gui = new GUI(env);

        env.events.sendEvent(new NewGridEvent(GridType.SQUARE, 20, 20));
    }

    public static void main(String[] args) {


        new Main();




    }

}
