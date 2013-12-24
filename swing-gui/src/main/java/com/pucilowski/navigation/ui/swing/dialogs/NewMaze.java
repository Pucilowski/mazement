package com.pucilowski.navigation.ui.swing.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Created by martin on 22/12/13.
 */
public class NewMaze extends JDialog {

    JComboBox<MazeType> types;
    JSlider width;
    JSlider height;



    public NewMaze(JFrame frame) {
        super(frame, "New Maze", true);


        setLayout(new GridLayout(0,1));

        initComponents();


        pack();
        setLocationRelativeTo(frame);
        setVisible(true);

    }

    private void initComponents() {
        types = new JComboBox<MazeType>();
        for (MazeType t : MazeType.values()) {
            types.addItem(t);
        }

        width = new JSlider(new DefaultBoundedRangeModel(30, 1, 2, 100));
        height = new JSlider(new DefaultBoundedRangeModel(30, 1, 2, 100));

        add(types);
        add(width);
        add(height);



    }

}
