package ui.classmates;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class ClassmatesList extends JPanel {
    Integer iD;
    String name;
    Vector<ArrayList<String>> classmatesInfo;

    // constructor takes a vector of strings containing the classmates' info
    ClassmatesList(Vector<ArrayList<String>> classmatesInfo){
        // reworked this. previously had hardcoded values. will also need to rework addClassmates function
        this.classmatesInfo = classmatesInfo;
        createAndDisplay();
        /*for (ArrayList<String> ar : classmatesInfo){
            for (String s : ar){
                System.out.print(s + ", ");
            }
            System.out.println();
        }*/
    }
    /*private static final String[] classmates = {
            "Carsyn Smeda",
            "Bryce Robinson",
            "Mikey Mathews",
            "Gabriel Goulis",
            "Carson Buntin"
    };

    private static final String[] classmatesPhones = {
            "849-135-1364",
            "124-362-3577",
            "642-246-6642",
            "133-920-9042",
            "399-230-0321"
    };

    private static final String[] classmatesEmails = {
            "carsyn_smeda1@baylor.edu",
            "bryce_robinson1@baylor.edu",
            "michael_mathews1@baylor.edu",
            "gabriel_goulis1@baylor.edu",
            "carson_buntin1@baylor.edu"
    };*/

    void createAndDisplay() {
        setMinimumSize(new Dimension(600, 350));
        setMaximumSize(new Dimension(600, 800));
        setAlignmentX(CENTER_ALIGNMENT);
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(520, 250);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(600, 300));
        panel.setMaximumSize(new Dimension(600, 8000));
        panel.add(addClassmates());
        scrolls.add(panel);
        add(scrolls);
    }

    Component addClassmates() {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setMinimumSize(new Dimension(600, 300));
        component.setMaximumSize(new Dimension(600, 8000));
        for (ArrayList<String> student : classmatesInfo){
            Classmate cm = new Classmate(student);
            component.add(cm);
        }
        return component;
    }
}
