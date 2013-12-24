package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.Main;
import com.pucilowski.navigation.ui.swing.Frame;

/**
 * Created by martin on 15/12/13.
 */
public class GUI {

    public final Main main;

    public Frame frame;


    public GUI(Main main) {
        this.main = main;
/*
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Unable to set native look and feel: " + e);
        }*/

        frame = new Frame(main,this);

    }

}
