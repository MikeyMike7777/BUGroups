package ui.tutors;

import javax.swing.*;
import java.awt.*;

public class TutorsList extends JPanel{
    private static final String[] tutorNames = {
            "Carsyn Smeda",
            "Bryce Robinson",
            "Mikey Mathews",
            "Gabriel Goulis",
            "Carson Buntin"
    };

    private static final String[] tutorClasses = {
            "REL 1310 - Christian Scriptures",
            "CSI 1430 - Introduction to Computer Science I",
            "STA 3381 - Probability and Statistics",
            "CSI 2334 - Computer Systems",
            "CSS 1302 - Speech for Business"
    };

    private static final String[] professors = {
            "John Smith",
            "Bill Booth",
            "Michael Gallaugher",
            "Cindy Fry",
            "Stephen Edwards"
    };

    private static final String[] semesters = {
            "Fall 2019",
            "Spring 2020",
            "Spring 2021",
            "Fall 2019",
            "Spring 2021"
    };

    private static final String[] rates = {
            "$10.00/hr",
            "$7.50/hr",
            "$12.00/hr",
            "$5.00/hr",
            "$8.00/hr"
    };

    TutorsList(){
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
        panel.add(addTutors(tutorNames, tutorClasses, professors, semesters,
                rates));
        scrolls.add(panel);
        add(scrolls);
    }

    Component addTutors(String[] names, String[] classes, String[] profs,
                        String[] sems, String[] hrates) {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setMinimumSize(new Dimension(600, 300));
        component.setMaximumSize(new Dimension(600, 8000));
        for (int i = 0; i < names.length; ++i) {
            TutoringOffer t = new TutoringOffer(tutorNames[i], tutorClasses[i],
                    professors[i], semesters[i], rates[i]);
            component.add(t);
        }
        return component;
    }
}
