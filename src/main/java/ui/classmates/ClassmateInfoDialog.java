package ui.classmates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class ClassmateInfoDialog extends JDialog{
    Classmate cm;
    Classmate parent;
    JPanel panel;
    ClassmateInfoDialog(Classmate owner){
        super(SwingUtilities.windowForComponent(owner));
        parent = owner;
        ArrayList<String> parentInfo = new ArrayList<>();
        parentInfo.add(parent.getName());
        parentInfo.add(parent.getEmail());
        parentInfo.add(parent.getPhone());
        parentInfo.add(parent.getAvailability()); // FIXME availability
        cm = new Classmate(parentInfo);
        cm.setFocusable(false);
        createAndDisplay();
    }

    void createAndDisplay() {
        setPreferredSize(new Dimension(600, 450));
        setTitle("View Classmate");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(600, 450));

        cm.removeMouseListener(cm.getMouseListeners()[0]);
        cm.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(new JLabel(" "));
        panel.add(new JLabel(cm.getName()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel(cm.getEmail()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel(cm.getPhone()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel(cm.getAvailability())); // FIXME: formatting probably
        panel.add(new JLabel(" "));

//        // availability-- same for everyone for demo purposes FIXME-- needs to be dynamic
//        panel.add(new JLabel("Availability:"));
//        panel.add(new JLabel("Monday: 8:00 AM - 12:00 PM"));
//        panel.add(new JLabel("Tuesday: 2:30 PM - 4:30 PM"));
//        panel.add(new JLabel("Wednesday: 3:00 PM - 3:30 PM, " +
//                "5:00 PM - 6:15 PM"));
//        panel.add(new JLabel("Thursday: 2:30 PM - 4:30 PM"));
//        panel.add(new JLabel("Friday: none"));
//        panel.add(new JLabel(" "));

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
