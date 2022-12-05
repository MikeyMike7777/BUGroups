package ui.profile;

import database.utils.BUGUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import ui.general.Window;

public class ProfileClassList extends JPanel {

    String[] classesDataDummy = {
            "CSI 3471",
            "WGS 2300"
    };

    Vector<String> classes;

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
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        // header label
        header = new JLabel("Current Classes:");

        add(header);

        buildClassList();
        add(classList);

        buildAddRemoveButtons();
        add(buttons);

    }

    void buildAddRemoveButtons(){
        JButton add = new JButton("Add Class");
        JButton remove = new JButton("Remove Class");

        add.addActionListener(new AddActionListener());
        remove.addActionListener(new RemoveActionListener());

        buttons.add(add);
        buttons.add(remove);

        buttons.setSize(new Dimension(10, 20));
        buttons.setVisible(true);
    }

    void buildClassList(){
        BUGUtils.controller.fetchProfileInfo(Window.username);
        if(classes != null) {
            model.addAll(classes);
        } else {
            model.addElement("No Current Classes");
        }
        classList = new JList<>(model);
        add(new JScrollPane(classList));
    }

    class AddActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddClassTutorDialog(model);
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
                    model.remove(classList.getSelectedIndex());
                }
            }
        }
    }

    public String[] getNames(){
        return classesDataDummy;
    }

}
