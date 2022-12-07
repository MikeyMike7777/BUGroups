package ui.classmates;

import database.utils.BUGUtils;
import ui.profile.ProfileClassList;
import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class ClassmatesTab extends JPanel{

    ProfileClassList list = new ProfileClassList();
    String[] classNames = new String[list.getNames().size() + 1];
    HashMap<String, Integer> boardKeys = new HashMap<>();
    JMenu select;

    ClassmatesTab() {
        super();
        loadClasses();
        createAndDisplay();
    }

    void loadClasses(){
        classNames[0] = "Select A Class";
        for (int i = 1; i < classNames.length; ++i) {
            classNames[i] = list.getNames().elementAt(i - 1);
            boardKeys.put(classNames[i], i);
        }
    }
    void createAndDisplay() {
        setSize(new Dimension(600, 400));
        setAlignmentX(LEFT_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addBoardMenu());
        add(panel);
    }

    Component addBoardMenu() {
        JMenuBar bar = new JMenuBar();
        bar.setPreferredSize(new Dimension(300, 30));
        select = new JMenu();
        select.setText("Select Class");
        select.setAlignmentX(CENTER_ALIGNMENT);
        select.setPreferredSize(new Dimension(300, 30));
        JMenuItem[] boards = createBoardOptions();
        for (int i = 0; i < classNames.length; ++i) {
            select.add(boards[i]);
        }
        bar.add(select);
        return bar;
    }

    JMenuItem[] createBoardOptions() {
        JMenuItem[] items = new JMenuItem[classNames.length];
        for (int i = 0; i < classNames.length; ++i) {
            items[i] = new JMenuItem(classNames[i]);
            if (i > 0)
                items[i].addActionListener(new ClassmatesTab.MenuActionListener());
        }
        return items;
    }

    class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = ((JMenuItem)e.getSource()).getText();
            ((JMenu)((JPopupMenu)((JMenuItem)e.getSource()).getParent()).
                    getInvoker()).setText(s);
            if (getComponentCount() > 1)
                remove(getComponentCount() - 1);

            // call controller
            Vector<ArrayList<String>> info = BUGUtils.controller.getClassmates(s.replaceAll(" ", ""), Window.username);
            if (info.isEmpty()) {
                add(new JLabel(" "));
                add(new JLabel("No other classmates!"));
            } else {
                ClassmatesList currClassmates = new ClassmatesList(info);
                currClassmates.iD = boardKeys.get(s);
                currClassmates.name = s;
                add(currClassmates);
            }
        }
    }

    public JMenu getClassMenu(){
        return select;
    }

    public String[] getClassNames(){
        return classNames;
    }

}


