package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.ui.dialogs.NewMaze;
import com.pucilowski.navigation.ui.panels.GridPanel;

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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


    private void initComponents() {


        // actions
        menuFileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog newMaze = new NewMaze();
                newMaze.setVisible(true);
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

        add( gridPanel, BorderLayout.CENTER);
        //add(controlPanel, BorderLayout.EAST);

    }

}
