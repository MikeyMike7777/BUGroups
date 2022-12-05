package ui.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

import database.student.Student;
import database.utils.BUGUtils;
import  ui.general.Window;

public class AddClassTutorDialog extends JDialog {

    JLabel codeLabel;

    JLabel sectionLabel;

    JLabel professorLabel;

    JPanel textPanel = new JPanel();

    JPanel buttonPanel = new JPanel();

    DefaultListModel<String> List;

    JTextField classCode = new JTextField(20);

    JTextField section = new JTextField(20);

    JTextField professor = new JTextField(20);

    String type;

    JPanel parent;


    AddClassTutorDialog(DefaultListModel<String> model, String s, JPanel parent) {
        super();
        this.parent = parent;
        List = model;
        type = s;
        setSize(300,350);
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

        cancel.addActionListener(new CancelActionListener());
        save.addActionListener(new SaveActionListener());

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        buttonPanel.setVisible(true);
    }

    void buildTextPanel() {
        String s;
        codeLabel = new JLabel("Enter Course Code:");
        sectionLabel = new JLabel("Enter Section:");
        professorLabel = new JLabel("Enter Professor:");
        textPanel.add(codeLabel);
        textPanel.add(classCode);
        textPanel.add(sectionLabel);
        textPanel.add(section);
        textPanel.add(professorLabel);
        textPanel.add(professor);
        textPanel.setVisible(true);
    }

    class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ( List.size() != 0 && Objects.equals(List.get(0), "No Current Classes!")){
                List.remove(0);
            }
            String sec = "";
            if (section.getText().length() < 2){
                sec = "0" + section.getText();
            }
            String normalizedCourseCode = classCode.getText().substring(0,3).toUpperCase(); // course
            String normalizedCourseNumber = classCode.getText().substring(classCode.getText().length() - 4);
            if(Objects.equals(type, "course")) {
                BUGUtils.controller.addCourse(normalizedCourseCode + normalizedCourseNumber + sec,
                        normalizedCourseCode + " " + normalizedCourseNumber,
                        sec, professor.getText());
                List.addElement(normalizedCourseCode + " " + normalizedCourseNumber + " " + sec);
            } else if (Objects.equals(type, "tutor")) {
                BUGUtils.controller.addTutorOffer(normalizedCourseCode , professor.getText(),"Fall 2022",2.50);

                List.addElement(normalizedCourseCode + " " + normalizedCourseNumber + " " + sec);
            }
            Window temp = (Window)(parent.getParent()
                    .getParent().getParent());
            temp.setVisible(false);
            temp.remove(1);
            temp.initNavigationBar();
            ((JTabbedPane)temp.getComponent(1)).setSelectedIndex(4);
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
