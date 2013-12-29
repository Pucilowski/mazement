package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.environment.Environment;

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
    }

    public static void main(String[] args) {

        new Main();

    }

}
