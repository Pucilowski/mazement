package com.pucilowski.navigation.ui.swing;

import com.pucilowski.navigation.ui.GUI;
import com.pucilowski.navigation.ui.events.NewMazeEvent;
import com.pucilowski.navigation.ui.swing.dialogs.MazeType;
import com.pucilowski.navigation.ui.swing.panels.GridPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by martin on 10/12/13.
 */
public class Frame extends JFrame {

    final GUI gui;


    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("Maze");
    JMenuItem menuFileNew = new JMenuItem("New");
    JMenuItem menuFileOpen = new JMenuItem("Open");
    JMenuItem menuFileSave = new JMenuItem("Save");


    public GridPanel gridPanel;

    public Frame(GUI gui) {
        this.gui = gui;

        setTitle("Navigation");

        initComponents();


        pack();
        setSize(1200, 900);
        setVisible(true);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    private void initComponents() {


        // actions
        menuFileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new maze");
                //JDialog newMaze = new NewMaze(Frame.this);

                JComboBox<MazeType> types = new JComboBox<MazeType>();
                JSlider width;
                JSlider height;


                for (MazeType t : MazeType.values()) {
                    types.addItem(t);
                }

                width = new JSlider(new DefaultBoundedRangeModel(30, 1, 2, 100));
                height = new JSlider(new DefaultBoundedRangeModel(30, 1, 2, 100));

                final JComponent[] inputs = new JComponent[]{
                        new JLabel("Type"),
                        types,
                        new JLabel("Width"),
                        width,
                        new JLabel("Height"),
                        height
                };

                //JOptionPane.showMessageDialog(null, inputs, "New maze...", JOptionPane.PLAIN_MESSAGE);
                int result = JOptionPane.showConfirmDialog(null, inputs, "New maze...", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {

                    NewMazeEvent event = new NewMazeEvent((MazeType) types.getSelectedItem(), width.getValue(), height.getValue());
                    gui.mazing.events.sendEvent(event);
                }

                System.out.println("Ya");

            }
        });


        menuBar.add(menuFile);

        menuFile.add(menuFileNew);
        menuFile.add(menuFileOpen);
        menuFile.add(menuFileSave);

        setJMenuBar(menuBar);

        setLayout(new BorderLayout());

        gridPanel = new GridPanel(gui.mazing);

        //ControlPanel controlPanel = new ControlPanel();
        //controlPanel.setPreferredSize(new Dimension(128, 600));

        add(gridPanel, BorderLayout.CENTER);
        //add(controlPanel, BorderLayout.EAST);

    }

}
