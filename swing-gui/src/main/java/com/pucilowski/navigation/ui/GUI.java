package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.Mazing;

/**
 * Created by martin on 15/12/13.
 */
public class GUI {

    public final Mazing mazing;

    Frame frame;

    public GUI(Mazing mazing) {
        this.mazing = mazing;

        frame = new Frame(this);

    }

}
