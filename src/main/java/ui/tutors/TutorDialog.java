package ui.tutors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TutorDialog extends JDialog{
    TutoringOffer t;
    TutoringOffer parent;
    JPanel panel;

    TutorDialog(TutoringOffer owner){
        super(SwingUtilities.windowForComponent(owner));
        parent = owner;
        ArrayList<String> parentInfo = new ArrayList<>();
        parentInfo.add(parent.getEmail() + "@baylor.edu");
        parentInfo.add(parent.getCourseCode());
        parentInfo.add(parent.getProfessor());
        parentInfo.add(parent.getSemester());
        parentInfo.add(parent.getHourlyRate());
        parentInfo.add(parent.getName());
        parentInfo.add(parent.getAvailability());
        t = new TutoringOffer(parentInfo);
        t.setFocusable(false);
        createAndDisplay();
    }

    void createAndDisplay() {
        setPreferredSize(new Dimension(600, 550));
        setTitle("View Tutoring Offer");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(600, 450));

        t.removeMouseListener(t.getMouseListeners()[0]);
        t.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(new JLabel(" "));
        panel.add(new JLabel("Tutor: " + t.getName()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Class: " + t.getCourseCode()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Professor taken: " + t.getProfessor()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Semester taken: " + t.getSemester()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Hourly rate: $" + t.getHourlyRate()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Email: " + t.getEmail()));
        panel.add(new JLabel(" "));

        panel.add(new JLabel("Availability:"));
        String[] availabilities = t.getAvailability().split(",");
        for (String s : availabilities){
            panel.add(new JLabel(s));
            panel.add(new JLabel(" "));
        }

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
