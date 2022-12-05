package ui.profile;

import database.student.Course;
import database.student.TutorOffer;
import database.utils.BUGUtils;
import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class ProfileTutorList extends JPanel {
    String[] classesDummyData = {
            "CSI 3336",
            "CSI 3471",
            "WGS 2300",
            "GEO 1306 "
    };

    Vector<String> tutors = new Vector<>();

    JLabel header;

    JList<String> tutorList;

    JPanel buttons = new JPanel();

    DefaultListModel<String> model = new DefaultListModel<>();

    public ProfileTutorList(){
        super();
        //GridLayout grid = new GridLayout(3, 1);
        //setLayout(grid);
        createAndDisplay();
    }

    void createAndDisplay() {
        setMinimumSize(new Dimension(100,100));
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        // header label
        header = new JLabel("Classes you are Tutoring:");

        add(header);

        buildClassList();
        add(tutorList);

        buildAddRemoveButtons();
        add(buttons);

    }

    void buildAddRemoveButtons(){
        JButton add = new JButton("Add Tutoring Offer");
        JButton remove = new JButton("Remove Tutoring");

        add.addActionListener(new AddActionListener());
        remove.addActionListener(new RemoveActionListener());

        buttons.add(add);
        buttons.add(remove);

        buttons.setSize(new Dimension(10, 20));
        buttons.setVisible(true);
    }

    void buildClassList(){
        Vector<Object> s = BUGUtils.controller.getStudentClasses(Window.username);
        for(int i = 0; i < s.size(); i++){
            tutors.add(s.elementAt(i).toString().substring(0,3) + " " + s.elementAt(i).toString().substring(3,7));
        }

        if(!tutors.isEmpty()) {
            model.addAll(tutors);
        } else {
            model.addElement("No Current Classes!");
        }
        tutorList = new JList<>(model);
        add(new JScrollPane(tutorList));
    }

    class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddClassTutorDialog(model, "tutor");
        }
    }

    class RemoveActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(tutorList.getSelectedValue() != null) {
                int answer = JOptionPane
                        .showConfirmDialog(null,
                                "Do you want to remove " + tutorList.getSelectedValue() + "?",
                                "Warning", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    model.remove(tutorList.getSelectedIndex());
                }
            }
        }
    }

    public String[] getNames(){
        return classesDummyData;
    }
}
