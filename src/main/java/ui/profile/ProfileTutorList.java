package ui.profile;

import database.utils.BUGUtils;
import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
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
        setMinimumSize(new Dimension(100,150));
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
        // pretend like this returns course numbers e.g. CSI 3322
        Vector<String> tutorOffers = BUGUtils.controller.getStudentTutors(Window.username);

        if(tutorOffers.size() > 0) {
            for (String course : tutorOffers) {
              //String offer = String.valueOf(BUGUtils.controller.fetchTutorOfferCourse(s));
              String formatted = course.substring(0, 3) + " " + course.substring (3);
              tutors.add(formatted);
            }
            model.addAll(tutors);
        } else {
            model.addElement("No Current Courses!");
        }

        tutorList = new JList<>(model);
        tutorList.setSize(50,50);
        add(new JScrollPane(tutorList));
    }

    class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddTutorOfferDialog(model, ProfileTutorList.this);
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
                    //model.remove(tutorList.getSelectedIndex());
                    String dbReadable = tutorList.getSelectedValue().replaceAll(" ", "");
                    model.remove(tutorList.getSelectedIndex());
                    BUGUtils.controller.removeTutoringOffer(Window.username, dbReadable);
                    Window temp = (Window)(getParent()
                            .getParent().getParent());
                    temp.setVisible(false);
                    temp.remove(1);
                    try {
                        temp.initNavigationBar();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    ((JTabbedPane)temp.getComponent(1)).setSelectedIndex(4);
                    temp.setVisible(true);
                }
            }
        }
    }

    public String[] getNames(){
        return classesDummyData;
    }
}
