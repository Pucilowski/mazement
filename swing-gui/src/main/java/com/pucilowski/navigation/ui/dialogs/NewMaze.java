package com.pucilowski.navigation.ui.dialogs;

import javax.swing.*;

/**
 * Created by martin on 22/12/13.
 */
public class NewMaze extends JDialog {

    JComboBox<MazeType> types;

    JSlider width;
    JSlider height;

    public NewMaze() {

        types = new JComboBox<MazeType>();
        for (MazeType t : MazeType.values()) {
            types.addItem(t);
        }

        DefaultBoundedRangeModel brm = new DefaultBoundedRangeModel(30, 1, 2, 100);

        width = new JSlider(brm);
        height = new JSlider(brm);

        add(types);
        add(width);
        add(height);

        setVisible(true);

    }

    public enum MazeType {
        SQUARE("Square"),
        HEXAGON("Hexagonal"),
        TRIANGLE("Triangular");

        final String label;

        MazeType(String label) {
            this.label = label;
        }
    }

}
