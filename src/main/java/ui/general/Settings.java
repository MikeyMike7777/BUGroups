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
                JPanel text = new JPanel();
                JButton done = new JButton("Done");
                JDialog changeDialog = new JDialog();

                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
                changePanel.add(label);
                text.add(new JTextField(15));
                changePanel.add(text);
                changePanel.add(done);

                label.setAlignmentX(CENTER_ALIGNMENT);
                done.setAlignmentX(CENTER_ALIGNMENT);
                changePanel.setAlignmentX(CENTER_ALIGNMENT);

                done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //updateName(from JTextField);
                        changeDialog.dispose();
                    }
                });

                changeDialog.add(changePanel);
                changeDialog.setSize(new Dimension(300, 100));
                changeDialog.setVisible(true);
            }
        });
        phoneNumber.setAlignmentX(CENTER_ALIGNMENT);
        return phoneNumber;
    }
    public void updateName(String name){
        Vector<Object> vector = BUGUtils.controller.fetchProfileInfo(Window.username);
    }
    private Component addChangePassword() {
        JButton phoneNumber = new JButton("Change Password");
        JLabel label = new JLabel("Change Password");
        JLabel label1 = new JLabel("Confirm Password");
        phoneNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel changePanel = new JPanel();
                JPanel text = new JPanel();
                JPanel text1 = new JPanel();
                JButton done = new JButton("Done");
                JDialog changeDialog = new JDialog();

                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
                changePanel.add(label);
                text.add(new JTextField(15));
                changePanel.add(text);
                changePanel.add(label1);
                text1.add(new JTextField(15));
                changePanel.add(text1);
                changePanel.add(done);

                label.setAlignmentX(CENTER_ALIGNMENT);
                label1.setAlignmentX(CENTER_ALIGNMENT);
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
                changeDialog.setSize(new Dimension(300, 160));
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
                JPanel text = new JPanel();
                JButton done = new JButton("Done");
                JDialog changeDialog = new JDialog();

                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
                changePanel.add(label);
                text.add(new JTextField(15));
                changePanel.add(text);
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
                changeDialog.setSize(new Dimension(300, 100));
                changeDialog.setVisible(true);
            }
        });
        phoneNumber.setAlignmentX(CENTER_ALIGNMENT);
        return phoneNumber;
    }
}
