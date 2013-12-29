package com.pucilowski.navigation.ui.swing.panels;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import java.awt.*;

/**
 * Created by martin on 11/12/13.
 */
public class ControlPanel extends JPanel {

    TreeModel tasks;

    JTree taskTree;
    JScrollPane taskPane;

    public ControlPanel() {

        //setBackground(Color.RED);

        setLayout(new BorderLayout());

        tasks = new TaskTreeModel();
        taskTree = new JTree(tasks);


        taskPane = new JScrollPane(taskTree);


        add(taskPane, BorderLayout.NORTH);

    }


    public class TaskTreeModel extends DefaultTreeModel {


         //DefaultMutableTreeNode root = ;

        public TaskTreeModel() {
            super(new DefaultMutableTreeNode("Root"));
        }

            @Override
        public Object getChild(Object parent, int index) {
            return null;
        }

        @Override
        public int getChildCount(Object parent) {
            return 0;
        }

        @Override
        public boolean isLeaf(Object node) {
            return false;
        }

        @Override
        public void valueForPathChanged(TreePath path, Object newValue) {

        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {
            return 0;
        }

        @Override
        public void addTreeModelListener(TreeModelListener l) {

        }

        @Override
        public void removeTreeModelListener(TreeModelListener l) {

        }
    }

}
