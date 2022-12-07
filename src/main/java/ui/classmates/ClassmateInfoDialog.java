package ui.classmates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClassmateInfoDialog extends JDialog{
    ClassmateBox cm;
    ClassmateBox parent;
    JPanel panel;
    ClassmateInfoDialog(ClassmateBox owner){
        super(SwingUtilities.windowForComponent(owner));
        parent = owner;
        ArrayList<String> parentInfo = new ArrayList<>();
        parentInfo.add(parent.getName());
        parentInfo.add(parent.getEmail());
        parentInfo.add(parent.getPhone());
        parentInfo.add(parent.getAvailability());
        cm = new ClassmateBox(parentInfo);
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
        panel.add(new JLabel("Name: " + cm.getName()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Email: " + cm.getEmail()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel("Phone number: " + cm.getPhone()));
        panel.add(new JLabel(" "));

        panel.add(new JLabel("Availability:"));
        String[] availabilities = cm.getAvailability().split(",");
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
