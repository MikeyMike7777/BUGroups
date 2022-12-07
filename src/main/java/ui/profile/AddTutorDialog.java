package ui.profile;

import database.utils.BUGUtils;
import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class AddTutorDialog extends JDialog{

    JLabel codeLabel;

    JLabel sectionLabel;

    JLabel professorLabel;
    JLabel hourlyRateLabel;

    JPanel textPanel = new JPanel();

    JPanel buttonPanel = new JPanel();

    DefaultListModel<String> List;

    JTextField classCode = new JTextField(20);

    JTextField section = new JTextField(20);

    JTextField professor = new JTextField(20);

    JTextField hourlyRate = new JTextField(20);

    String type;

    JPanel parent;


    AddTutorDialog(DefaultListModel<String> model, String s, JPanel parent) {
        super();
        this.parent = parent;
        List = model;
        type = s;
        setSize(300,410);
        createAndDisplay();
    }

    void createAndDisplay() {
        setLayout(new GridLayout(2,2));

        buildTextPanel();
        add(textPanel);

        buildSaveCancel();
        add(buttonPanel);

        setVisible(true);
    }

    void buildSaveCancel(){
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");

        cancel.addActionListener(new AddTutorDialog.CancelActionListener());
        save.addActionListener(new AddTutorDialog.SaveActionListener());

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        buttonPanel.setVisible(true);
    }

    void buildTextPanel() {
        String s;
        codeLabel = new JLabel("Enter Course Code:");
        professorLabel = new JLabel("Enter Professor You took:");
        sectionLabel = new JLabel("Enter Semester Taken:");
        hourlyRateLabel = new JLabel("Enter hourly rate:");


        textPanel.add(codeLabel);
        textPanel.add(classCode);
        textPanel.add(sectionLabel);
        textPanel.add(section);
        textPanel.add(professorLabel);
        textPanel.add(professor);
        textPanel.add(hourlyRateLabel);
        textPanel.add(hourlyRate);
        textPanel.setVisible(true);
    }

    class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (classCode.getText().matches("[a-zA-Z]{3} ?[1-4][1-5][0-9]{2}")) {
                if (!section.getText().isEmpty()) {
                    if (!professor.getText().isEmpty()) {
                        if (!hourlyRate.getText().matches("[0-9]{1,9}.?[0-9]{0,2}?")) {
                            if (List.size() != 0 && Objects.equals(List.get(0), "No Current Classes!")) {
                                List.remove(0);
                            }

                            String normalizedCourseCode = classCode.getText().substring(0, 3).toUpperCase(); // course
                            String normalizedCourseNumber = classCode.getText().substring(classCode.getText().length() - 4);

                            BUGUtils.controller.addTutorOffer(Window.username, normalizedCourseCode + normalizedCourseNumber, professor.getText(),
                                    section.getText(), Double.parseDouble(hourlyRate.getText()));
                            List.addElement(normalizedCourseCode + " " + normalizedCourseNumber);

                            ui.general.Window temp = (Window) (parent.getParent()
                                    .getParent().getParent());
                            temp.setVisible(false);
                            temp.remove(1);
                            try {
                                temp.initNavigationBar();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            ((JTabbedPane) temp.getComponent(1)).setSelectedIndex(4);
                            temp.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(getRootPane().getParent(),
                                    "Please enter a valid decimal value.",
                                    null, JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(getRootPane().getParent(),
                                "Please enter a professor name.",
                                null, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(getRootPane().getParent(),
                            "Please enter a semester.",
                            null, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(getRootPane().getParent(),
                        "Please enter a valid course code.",
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class CancelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

}

