package com.pucilowski.navigation.ui.dialogs;

import javax.swing.*;

/**
 * Created by martin on 22/12/13.
 */
public class NewMaze extends JDialog {

    JComboBox<String> types;

    JSlider width;
    JSlider height;

    public NewMaze() {

        types = new JComboBox<>();
        types.addItem("Square");
        types.addItem("Hexagon");
        types.addItem("Triangle");

        DefaultBoundedRangeModel brm = new DefaultBoundedRangeModel(30, 1, 2, 100);

        width = new JSlider(brm);
        height = new JSlider(brm);

        add(types);
        add(width);
        add(height);

        setVisible(true);

    }

    public enum Maze {
        SQUARE(),
        HEXAGON,
        TRIANGLE
    }

}
