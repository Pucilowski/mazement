package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.environment.enums.GridType;
import com.pucilowski.navigation.environment.events.NewGridEvent;
import com.pucilowski.navigation.ui.swing.Frame;

/**
 * Created by martin on 15/12/13.
 */
public class GUI {

    public final Environment env;

    public Frame frame;


    public GUI(Environment env) {
        this.env = env;


//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            System.out.println("Unable to set native look and feel: " + e);
//        }

        frame = new Frame(env, this);


        env.events.sendEvent(new NewGridEvent(GridType.HEXAGON, 40, 20));
    }

}
