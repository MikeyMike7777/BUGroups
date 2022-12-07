package ui.tutors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class TutorsList extends JPanel{
    Vector<ArrayList<String>> tutorOffersInfo;

    TutorsList(Vector<ArrayList<String>> tutorOffersInfo){
        // reworked this. previously had hardcoded values. will also need to rework addClassmates function
        this.tutorOffersInfo = tutorOffersInfo;
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
            //tutorOffer.remove(0);
            TutoringOffer t = new TutoringOffer(tutorOffer);
            component.add(t);
        }
        return component;
    }
}
