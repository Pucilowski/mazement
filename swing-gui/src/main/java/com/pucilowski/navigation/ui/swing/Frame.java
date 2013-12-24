package com.pucilowski.navigation.ui.swing;

import com.pucilowski.navigation.ui.GUI;
import com.pucilowski.navigation.ui.events.GenerateMazeEvent;
import com.pucilowski.navigation.ui.events.NewGridEvent;
import com.pucilowski.navigation.ui.events.SolveMazeEvent;
import com.pucilowski.navigation.ui.swing.dialogs.enums.GridType;
import com.pucilowski.navigation.ui.swing.dialogs.enums.MazeGenerator;
import com.pucilowski.navigation.ui.swing.dialogs.enums.MazePathfinder;
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

    JMenu menuGenerate = new JMenu("Generate");

    JMenu menuPathfinding = new JMenu("Solve");

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
                menuFileNew();
            }
        });
        menuFileSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFileSave();
            }
        });


        menuBar.add(menuFile);

        menuFile.add(menuFileNew);
        menuFile.add(menuFileOpen);
        menuFile.add(menuFileSave);


        for (final MazeGenerator g : MazeGenerator.values()) {
            JMenuItem gItem = new JMenuItem(g.toString());
            menuGenerate.add(gItem);

            gItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuGenerateMaze(g);
                }
            });
        }

        menuBar.add(menuGenerate);


        for (final MazePathfinder g : MazePathfinder.values()) {
            JMenuItem gItem = new JMenuItem(g.toString());
            menuPathfinding.add(gItem);

            gItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuSolveMaze(g);
                }
            });
        }

        menuBar.add(menuPathfinding);


        setJMenuBar(menuBar);

        setLayout(new BorderLayout());

        gridPanel = new GridPanel(gui.mazing);

        //ControlPanel controlPanel = new ControlPanel();
        //controlPanel.setPreferredSize(new Dimension(128, 600));

        add(gridPanel, BorderLayout.CENTER);
        //add(controlPanel, BorderLayout.EAST);

    }


    public void menuFileNew() {
        //JDialog newMaze = new NewMaze(Frame.this);

        JComboBox<GridType> types = new JComboBox<GridType>();
        JSlider width;
        JSlider height;

        for (GridType t : GridType.values()) {
            types.addItem(t);
        }

        width = new JSlider(new DefaultBoundedRangeModel(30, 1, 2, 100));
        height = new JSlider(new DefaultBoundedRangeModel(30, 1, 2, 100));

        final JComponent[] inputs = new JComponent[]{
                new JLabel("Type"), types,
                new JLabel("Width"), width,
                new JLabel("Height"), height
        };

        //JOptionPane.showMessageDialog(null, inputs, "New maze...", JOptionPane.PLAIN_MESSAGE);
        int result = JOptionPane.showConfirmDialog(this, inputs, "New maze...", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {

            NewGridEvent event = new NewGridEvent((GridType) types.getSelectedItem(), width.getValue(), height.getValue());
            gui.mazing.events.sendEvent(event);
        }


    }

    private void menuFileOpen() {

        final JFileChooser fc = new JFileChooser();

        fc.showSaveDialog(this);

    }

    private void menuFileSave() {

        final JFileChooser fc = new JFileChooser();

        fc.showSaveDialog(this);

    }

    public void menuGenerateMaze(MazeGenerator gen) {

        GenerateMazeEvent event = new GenerateMazeEvent(gen);
        gui.mazing.events.sendEvent(event);

    }

    public void menuSolveMaze(MazePathfinder gen) {

        SolveMazeEvent event = new SolveMazeEvent(gen);
        gui.mazing.events.sendEvent(event);

    }

}
