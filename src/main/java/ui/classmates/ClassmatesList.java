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
        panel.add(addClassmates(classmates));
        scrolls.add(panel);
        add(scrolls);
    }

    Component addClassmates(String[] names) {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setMinimumSize(new Dimension(600, 300));
        component.setMaximumSize(new Dimension(600, 8000));
        for (int i = 0; i < names.length; ++i) {
            Classmate cm = new Classmate(names[i]);
            component.add(cm);
        }
        return component;
    }
}
