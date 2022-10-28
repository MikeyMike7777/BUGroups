package ui.classmates;

import javax.swing.*;
import java.awt.*;

public class ClassmatesList extends JPanel {
    Integer iD;
    String name;
    private static final String[] classmates = {
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
    };

    ClassmatesList(){
        super();
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
        panel.add(addClassmates(classmates, classmatesEmails, classmatesPhones));
        scrolls.add(panel);
        add(scrolls);
    }

    Component addClassmates(String[] names, String[] emails, String[] phones) {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setMinimumSize(new Dimension(600, 300));
        component.setMaximumSize(new Dimension(600, 8000));
        for (int i = 0; i < names.length; ++i) {
            Classmate cm = new Classmate(names[i], emails[i], phones[i]);
            component.add(cm);
        }
        return component;
    }
}
