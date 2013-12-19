package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.maze.generation.RecursiveDFS;
import com.pucilowski.navigation.maze.generation.StackDFS;
import com.pucilowski.navigation.maze.model.grid.Grid;
import com.pucilowski.navigation.ui.panels.ControlPanel;
import com.pucilowski.navigation.ui.panels.ViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by martin on 10/12/13.
 */
public class Frame extends JFrame {

    final GUI gui;

    public Frame(GUI gui) {
        this.gui = gui;

        setTitle("Navigation");

        initComponents();


        pack();
        setSize(800, 600);
        setVisible(true);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void initComponents() {


        JMenuBar menuBar = new JMenuBar();
        JMenu generateItem = new JMenu("Generate");
        JMenuItem genDfsStack = new JMenuItem("DFS - Stack");
        JMenuItem genDfsRecursive = new JMenuItem("DFS - Recursive");

        // actions
        genDfsStack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StackDFS dfs = new StackDFS(gui.mazing.grid);
            }
        });
        genDfsRecursive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecursiveDFS dfs = new RecursiveDFS(gui.mazing.grid);
            }
        });

        menuBar.add(generateItem);
        generateItem.add(genDfsStack);
generateItem.add(genDfsRecursive);

        setJMenuBar(menuBar);
        setLayout(new BorderLayout());


        ViewPanel viewPanel = new ViewPanel() {
            @Override
            public Grid getGrid() {
                return gui.mazing.grid;
            }
        };

        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setPreferredSize(new Dimension(128, 600));

        add(viewPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);

    }

}
