package ui.general;

import database.student.Student;
import database.utils.BUGUtils;
import database.utils.Controller;
import ui.classmates.ClassmatesTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Settings extends JPanel {
    ClassmatesTab dropdown;
    public Settings(Dimension preferredSize) {
        super();
        initializeWindow(preferredSize);
    }

    private void initializeWindow(Dimension preferredSize) {
        setMinimumSize(preferredSize);
        addComponents();
    }

    private void addComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addLabel());
        panel.add(addChangeName());
        panel.add(addChangeEmail());
        panel.add(addChangePassword());
        panel.add(addChangePhoneNumber());
        panel.setAlignmentX(CENTER_ALIGNMENT);
        add(panel);
    }

    private Component addLabel() {
        JLabel label = new JLabel("Settings");
        label.setAlignmentX(CENTER_ALIGNMENT);
        return label;
    }

    private Component addChangeName() {
        JButton phoneNumber = new JButton("Change Name");
        JLabel label = new JLabel("Change Name");
        phoneNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel changePanel = new JPanel();
                JTextField changeName = new JTextField();
                JButton done = new JButton("Done");
                JDialog changeDialog = new JDialog();

                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
                changePanel.add(label);
                changePanel.add(changeName);
                changePanel.add(done);

                label.setAlignmentX(CENTER_ALIGNMENT);
                done.setAlignmentX(CENTER_ALIGNMENT);
                changePanel.setAlignmentX(CENTER_ALIGNMENT);

                done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Vector<Object> vector = BUGUtils.controller.fetchProfileInfo(Window.username);
                        changeDialog.dispose();
                    }
                });

                changeDialog.add(changePanel);
                changeDialog.setVisible(true);
            }
        });
        phoneNumber.setAlignmentX(CENTER_ALIGNMENT);
        return phoneNumber;
    }
    private Component addChangePassword() {
        JButton phoneNumber = new JButton("Change Password");
        JLabel label = new JLabel("Change Password");
        phoneNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel changePanel = new JPanel();
                JTextField changePassword = new JTextField();
                JButton done = new JButton("Done");
                JDialog changeDialog = new JDialog();

                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
                changePanel.add(label);
                changePanel.add(changePassword);
                changePanel.add(done);

                label.setAlignmentX(CENTER_ALIGNMENT);
                done.setAlignmentX(CENTER_ALIGNMENT);
                changePanel.setAlignmentX(CENTER_ALIGNMENT);
                done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //updatePassword();
                        changeDialog.dispose();
                    }
                });

                changeDialog.add(changePanel);
                changeDialog.setVisible(true);
            }
        });
        phoneNumber.setAlignmentX(CENTER_ALIGNMENT);
        return phoneNumber;
    }

    private Component addChangePhoneNumber() {
        JButton phoneNumber = new JButton("Change Phone Number");
        JLabel label = new JLabel("Change Phone Number");
        phoneNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel changePanel = new JPanel();
                JTextField changePhone = new JTextField();
                JButton done = new JButton("Done");
                JDialog changeDialog = new JDialog();

                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
                changePanel.add(label);
                changePanel.add(changePhone);
                changePanel.add(done);

                label.setAlignmentX(CENTER_ALIGNMENT);
                done.setAlignmentX(CENTER_ALIGNMENT);
                changePanel.setAlignmentX(CENTER_ALIGNMENT);
                done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //updatePhoneNumber();
                        changeDialog.dispose();
                    }
                });

                changeDialog.add(changePanel);
                changeDialog.setVisible(true);
            }
        });
        phoneNumber.setAlignmentX(CENTER_ALIGNMENT);
        return phoneNumber;
    }
}
