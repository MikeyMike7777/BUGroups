package ui.tutors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorDialog extends JDialog{
    TutoringOffer t;
    TutoringOffer parent;
    JPanel panel;

    TutorDialog(TutoringOffer owner){
        super(SwingUtilities.windowForComponent(owner));
        parent = owner;
        t = new TutoringOffer(parent.getName(), parent.getClassTutoring(),
                parent.getProfessor(), parent.getSemester(), parent.getHourlyRate());
        t.setFocusable(false);
        createAndDisplay();
    }

    void createAndDisplay() {
        setPreferredSize(new Dimension(600, 450));
        setTitle("View Tutoring Offer");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(600, 450));

        t.removeMouseListener(t.getMouseListeners()[0]);
        t.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(new JLabel(" "));
        panel.add(new JLabel("Tutor: " + t.getName()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Class: " + t.getClassTutoring()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Professor taken: " + t.getProfessor()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Semester taken: " + t.getSemester()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Hourly rate: " + t.getHourlyRate()));
        panel.add(new JLabel(" "));

        // availability-- same for everyone for demo purposes
        panel.add(new JLabel("Availability:"));
        panel.add(new JLabel("Monday: 8:00 AM - 12:00 PM"));
        panel.add(new JLabel("Tuesday: 2:30 PM - 4:30 PM"));
        panel.add(new JLabel("Wednesday: 3:00 PM - 3:30 PM, " +
                "5:00 PM - 6:15 PM"));
        panel.add(new JLabel("Thursday: 2:30 PM - 4:30 PM"));
        panel.add(new JLabel("Friday: none"));
        panel.add(new JLabel(" "));

        add(panel);
        addButton();

        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    void addButton() {
        JButton close = new JButton("Close");
        close.setAlignmentX(Component.CENTER_ALIGNMENT);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(close);
    }
}
