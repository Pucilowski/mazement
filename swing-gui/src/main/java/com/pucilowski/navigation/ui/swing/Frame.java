package com.pucilowski.navigation.ui.swing;

import com.pucilowski.navigation.Main;
import com.pucilowski.navigation.ui.GUI;
import com.pucilowski.navigation.ui.events.GenerateMazeEvent;
import com.pucilowski.navigation.ui.events.NewGridEvent;
import com.pucilowski.navigation.ui.events.SolveMazeEvent;
import com.pucilowski.navigation.ui.swing.dialogs.enums.GridType;
import com.pucilowski.navigation.ui.swing.dialogs.enums.MazeGenerator;
import com.pucilowski.navigation.ui.swing.dialogs.enums.MazePathfinder;
import com.pucilowski.navigation.ui.swing.panels.GridPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by martin on 10/12/13.
 */
public class Frame extends JFrame {

    final Main main;
    final GUI gui;

    JMenuBar menuBar = new JMenuBar();

    JMenu menuFile = new JMenu("Maze");
    JMenuItem menuFileNew = new JMenuItem("New");
    JMenuItem menuFileOpen = new JMenuItem("Open");
    JMenuItem menuFileSave = new JMenuItem("Save");

    JMenu menuGenerate = new JMenu("Generate");

    JMenu menuPathfinding = new JMenu("Solve");

    public GridPanel gridPanel;

    public Frame(Main main,GUI gui) {
        this.main=main;
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

        gridPanel = new GridPanel(gui.main);



       /* JPanel panel = new TexturedPanel(new Color(85, 85, 85), new Color(116, 116, 116), new boolean[][]{
                new boolean[]{false, false, false, true},
                new boolean[]{false, false, true, false},
                new boolean[]{false, true, false, false},
                new boolean[]{true, false, false, false}
        });*/

        //dad.setBackground(Color.BLUE);
        /*panel.setLayout(new MigLayout("insets 0",
                "[grow,center]",
                "[grow,center]"));*/
        //panel.setLayout(new BorderLayout());
      //  panel.setLayout(new GridBagLayout());
        //panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));


        //gridPanel.setSize(100,100);
      //  gridPanel.setPreferredSize(new Dimension(100,100));
        //panel.add(gridPanel, BorderLayout.CENTER);
        //gridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
      //  panel.add(gridPanel);

        //new JScrollPane(panel);



        //ControlPanel controlPanel = new ControlPanel();
        //controlPanel.setPreferredSize(new Dimension(128, 600));

        add(gridPanel, BorderLayout.CENTER);
        //add(controlPanel, BorderLayout.EAST);

    }


    public void menuFileNew() {
        //JDialog newMaze = new NewMaze(Frame.this);

        JComboBox<GridType> types = new JComboBox<GridType>();
        final JSlider width;
        final JSlider height;

        for (GridType t : GridType.values()) {
            types.addItem(t);
        }

        //width = new JSlider(new DefaultBoundedRangeModel(30, 1, 2, 100));

        int w = gui.main.grid.width;
        int h = gui.main.grid.height;

        final JLabel widthLabel = new JLabel("Width: " + w);
        width = new JSlider(0, 100, w);
        width.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                widthLabel.setText("Width: " + width.getValue());
            }
        });

        final JLabel heightLabel = new JLabel("Height: " + h);
        height = new JSlider(0, 100, h);
        height.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                heightLabel.setText("Height: " + height.getValue());
            }
        });

        final JComponent[] inputs = new JComponent[]{
                new JLabel("Type"), types,
                widthLabel, width,
                heightLabel, height
        };

        //JOptionPane.showMessageDialog(null, inputs, "New maze...", JOptionPane.PLAIN_MESSAGE);
        int result = JOptionPane.showConfirmDialog(this, inputs, "New maze...", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            NewGridEvent event = new NewGridEvent((GridType) types.getSelectedItem(), width.getValue(), height.getValue());
            gui.main.events.sendEvent(event);
        }


    }

    private void menuFileOpen() {

        final JFileChooser fc = new JFileChooser();

        fc.showOpenDialog(this);

    }

    private void menuFileSave() {

        final JFileChooser fc = new JFileChooser();

        fc.showSaveDialog(this);

    }

    public void menuGenerateMaze(MazeGenerator gen) {

        GenerateMazeEvent event = new GenerateMazeEvent(gen);
        gui.main.events.sendEvent(event);

    }

    public void menuSolveMaze(MazePathfinder gen) {

        SolveMazeEvent event = new SolveMazeEvent(gen);
        gui.main.events.sendEvent(event);

    }

}
