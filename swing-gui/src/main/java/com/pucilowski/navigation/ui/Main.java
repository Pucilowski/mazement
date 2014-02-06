package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.environment.enums.GridType;
import com.pucilowski.navigation.environment.events.NewGridEvent;

/**
 * Created by martin on 29/12/13.
 */
public class Main extends Environment {

    GUI gui;

    public Main() {
        gui = new GUI(this);

        events.sendEvent(new NewGridEvent(GridType.SQUARE, 20, 20));
    }

    @Override
    public void onRefresh() {
        gui.frame.repaint();
    }

    public static void main(String[] args) {
        new Main();
    }

}
