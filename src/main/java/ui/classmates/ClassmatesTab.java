package ui.classmates;

import ui.profile.ProfileClassList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ClassmatesTab extends JPanel{

    ProfileClassList list = new ProfileClassList();
    String [] classNames = new String[list.getNames().length + 1];
    HashMap<String, Integer> boardKeys = new HashMap<>();
    //HashMap<String, JPanel> classes = new HashMap<>();
    //JPanel currClass = new JPanel();

    ClassmatesTab() {
        super();
        loadClasses();
        createAndDisplay();
    }

    void loadClasses(){
        classNames[0] = "Select A Class";
        for (int i = 1; i < classNames.length; ++i) {
            classNames[i] = list.getNames()[i - 1];
            boardKeys.put(classNames[i], i);
            //JPanel oneClass = new JPanel();
            // set box layout for each panel
            //oneClass.setLayout(new BoxLayout(oneClass, BoxLayout.Y_AXIS));
            // add label for class in each jpanel
            //oneClass.add(new JLabel(classNames[i]));
            // add to map
            //classes.put(classNames[i], oneClass);
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
        //demoSoftwareClass();
    }

    Component addBoardMenu() {
        JMenuBar bar = new JMenuBar();
        bar.setPreferredSize(new Dimension(300, 30));
        JMenu select = new JMenu();
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
            //currClass = classes.get(s);
            if (getComponentCount() > 1)
                remove(getComponentCount() - 1);
            ClassmatesList currClassmates = new ClassmatesList();
            currClassmates.iD = boardKeys.get(s);
            currClassmates.name = s;
            add(currClassmates);
        }
    }

    /*void demoSoftwareClass(){
        // get the software engineering jpanel and add hardcoded demo data
        classes.get("CSI 3471 - Software Engineering I").add(new JLabel("Carson Buntin"));
        classes.get("CSI 3471 - Software Engineering I").add(new JLabel("Mikey Mathews"));
        classes.get("CSI 3471 - Software Engineering I").add(new JLabel("Gabe Goulis"));
        classes.get("CSI 3471 - Software Engineering I").add(new JLabel("Bryce Robinson"));
    }*/

    /*
    when user clicks "update classes" in profile, it should construct a new
    Classmates Tab object. ClassList (in profile) should start with nothing in it
     */
}


