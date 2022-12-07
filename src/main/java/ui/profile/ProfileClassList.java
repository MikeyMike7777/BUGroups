package ui.profile;

import database.utils.BUGUtils;

import javax.print.attribute.standard.JobStateReason;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import ui.general.Window;

public class ProfileClassList extends JPanel {

    Vector<String> classes = new Vector<>();

    JLabel header;

    JList<String> classList;

    JPanel buttons = new JPanel();

    DefaultListModel<String> model = new DefaultListModel<>();

    public ProfileClassList(){
        super();
        createAndDisplay();
    }

    void createAndDisplay() {
        setMinimumSize(new Dimension(100,100));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        // header label
        header = new JLabel("Current enrolled courses:");
        header.setAlignmentX(CENTER_ALIGNMENT);

        add(header);

        buildAddRemoveButtons();
        buttons.setAlignmentX(CENTER_ALIGNMENT);
        add(buttons);

        buildClassList();
        classList.setAlignmentX(CENTER_ALIGNMENT);
        add(classList);

    }

    void buildAddRemoveButtons(){
        JButton add = new JButton("Add Class");
        JButton remove = new JButton("Remove Class");

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        add.addActionListener(new AddActionListener());
        remove.addActionListener(new RemoveActionListener());

        buttons.add(add);
        buttons.add(remove);

        buttons.setSize(new Dimension(10, 20));
        buttons.setVisible(true);
    }

    void buildClassList(){
        ArrayList<String> courses = BUGUtils.controller.getStudentCourses(Window.username);
        if (!courses.isEmpty()){
            for (String s : courses){
                String formatted = s.substring(0, 3) + " " + s.substring (3,7) + " " + s.substring(7);
                classes.add(formatted);
            }
            model.addAll(classes);
        } else {
            model.addElement("No Current Classes!");
        }

        classList = new JList<>(model);
        classList.setSize(50,50);
    }

    class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddCourseDialog(model, ProfileClassList.this);
        }
    }

    class RemoveActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(classList.getSelectedValue() != null) {
                int answer = JOptionPane
                        .showConfirmDialog(null,
                                "Do you want to remove " + classList.getSelectedValue() + "?",
                                "Warning", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    String dbReadable = classList.getSelectedValue().replaceAll(" ", ""); // section
                    model.remove(classList.getSelectedIndex());
                    BUGUtils.controller.removeCourse(Window.username, dbReadable);
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

    public Vector<String> getNames(){
        return classes;
    }

}
