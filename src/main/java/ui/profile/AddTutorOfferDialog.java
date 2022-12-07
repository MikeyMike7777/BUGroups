package ui.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

import database.utils.BUGUtils;
import ui.general.Window;

public class AddTutorOfferDialog extends JDialog{

    JLabel codeLabel;

    JLabel semesterLabel;

    JLabel professorLabel;
    JLabel hourlyRateLabel;

    JPanel textPanel = new JPanel();

    JPanel buttonPanel = new JPanel();

    DefaultListModel<String> List;

    JTextField classCode = new JTextField(20);

    JTextField professor = new JTextField(20);

    JTextField hourlyRate = new JTextField(20);
    JTextField semester = new JTextField(20);

    JPanel parent;


    AddTutorOfferDialog(DefaultListModel<String> model, JPanel parent) {
        super();
        this.parent = parent;
        List = model;
        setSize(300, 550);
        createAndDisplay();
    }

    void createAndDisplay() {
        setLayout(new GridLayout(2, 2));

        buildTextPanel();
        add(textPanel);

        buildSaveCancel();
        add(buttonPanel);

        setVisible(true);
    }

    void buildSaveCancel() {
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");

        cancel.addActionListener(new CancelActionListener());
        save.addActionListener(new SaveActionListener());

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        buttonPanel.setVisible(true);
    }

    void buildTextPanel() {
        String s;
        codeLabel = new JLabel("Enter Course Code:");
        semesterLabel = new JLabel("Enter Semester Taken:");
        professorLabel = new JLabel("Enter Professor Taken: ");
        hourlyRateLabel = new JLabel("Enter Hourly Rate: ");
        textPanel.add(codeLabel);
        textPanel.add(classCode);
        textPanel.add(semesterLabel);
        textPanel.add(semester);
        textPanel.add(professorLabel);
        textPanel.add(professor);
        textPanel.add(hourlyRateLabel);
        textPanel.add(hourlyRate);
        textPanel.setVisible(true);
    }

    class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (List.size() != 0 && Objects.equals(List.get(0), "No Current Offers!")) {
                List.remove(0);
            }
            String normalizedCourseCode = classCode.getText().substring(0, 3).toUpperCase(); // course e.g. CSI
            String normalizedCourseNumber = classCode.getText().substring(classCode.getText().length() - 4); // number e.g 2334
            BUGUtils.controller.addTutorOffer(Window.username,
                    normalizedCourseCode + normalizedCourseNumber,
                    professor.getText(),
                    semester.getText(),
                    Double.parseDouble(hourlyRate.getText()));
            List.addElement(normalizedCourseCode + " " + normalizedCourseNumber);
            Window temp = (Window) (parent.getParent()
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
        }
    }

    class CancelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

}
