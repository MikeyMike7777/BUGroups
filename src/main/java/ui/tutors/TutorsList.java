package ui.tutors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class TutorsList extends JPanel{
    Vector<ArrayList<String>> tutorOffersInfo;

    /*private static final String[] tutorNames = {
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
    };*/

    TutorsList(Vector<ArrayList<String>> tutorOffersInfo){
        // reworked this. previously had hardcoded values. will also need to rework addClassmates function
        this.tutorOffersInfo = tutorOffersInfo;
        System.out.println("size of tutorOffersInfo: " + tutorOffersInfo.size());
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
        panel.add(addTutors());
        scrolls.add(panel);
        add(scrolls);
    }

    Component addTutors() {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setMinimumSize(new Dimension(600, 300));
        component.setMaximumSize(new Dimension(600, 8000));
        for (ArrayList<String> tutorOffer : tutorOffersInfo){
            TutoringOffer t = new TutoringOffer(tutorOffer);
            component.add(t);
        }
        return component;
    }
}
