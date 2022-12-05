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
    }

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
