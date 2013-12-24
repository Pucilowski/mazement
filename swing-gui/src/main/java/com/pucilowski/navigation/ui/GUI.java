package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.Mazing;
import com.pucilowski.navigation.ui.swing.Frame;

import javax.swing.*;

/**
 * Created by martin on 15/12/13.
 */
public class GUI {

    public final Mazing mazing;

    public Frame frame;


    public GUI(Mazing mazing) {
        this.mazing = mazing;
/*
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Unable to set native look and feel: " + e);
        }*/

        frame = new Frame(this);

    }

}
