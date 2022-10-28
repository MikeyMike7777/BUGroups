package ui.classmates;

import ui.messages.MessageDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassmateInfoDialog extends JDialog{
    Classmate cm;
    Classmate parent;
    JPanel panel;
    ClassmateInfoDialog(Classmate owner){
        super(SwingUtilities.windowForComponent(owner));
        parent = owner;
        cm = new Classmate(parent.getName(), parent.getEmail(),
                parent.getPhone());
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

        panel.add(new JLabel(cm.getName()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel(cm.getEmail()));
        panel.add(new JLabel(" "));
        panel.add(new JLabel(cm.getPhone()));
        panel.add(new JLabel(" "));

        addButton();
        add(panel);

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
