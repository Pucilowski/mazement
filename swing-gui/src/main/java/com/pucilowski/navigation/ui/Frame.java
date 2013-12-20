package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.maze.algorithms.generation.RecursiveDFS;
import com.pucilowski.navigation.maze.algorithms.generation.StackDFS;
import com.pucilowski.navigation.maze.model.grid.Grid;
import com.pucilowski.navigation.maze.model.grid.SqGrid;
import com.pucilowski.navigation.ui.panels.ControlPanel;
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

    public GridPanel gridPanel;

    public Frame(GUI gui) {
        this.gui = gui;

        setTitle("Navigation");

        initComponents();


        pack();
        setSize(1024, 768);
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
                gui.mazing.grid = new SqGrid(20,15);
                StackDFS dfs = new StackDFS(gui.mazing.grid);
            }
        });
        genDfsRecursive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.mazing.grid = new SqGrid(20,15);

                RecursiveDFS dfs = new RecursiveDFS(gui.mazing.grid);
            }
        });

        menuBar.add(generateItem);
        generateItem.add(genDfsStack);
generateItem.add(genDfsRecursive);

        setJMenuBar(menuBar);
        setLayout(new BorderLayout());


        gridPanel = new GridPanel() {
            @Override
            public Grid getGrid() {
                return gui.mazing.grid;
            }
        };


        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setPreferredSize(new Dimension(128, 600));

        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);

    }

}
