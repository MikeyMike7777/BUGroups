package ui.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClassTutorDialog extends JDialog {

    JLabel addLabel;

    JPanel textPanel = new JPanel();

    JPanel buttonPanel = new JPanel();

    DefaultListModel<String> List;

    JTextField field = new JTextField(20);


    AddClassTutorDialog(DefaultListModel<String> model, String label) {
        super();
        List = model;
        addLabel = new JLabel(label);
        setSize(300,120);
        createAndDisplay();
    }

    void createAndDisplay() {
        setLayout(new FlowLayout());

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

    void buildTextPanel(){
        textPanel.add(addLabel);
        textPanel.add(field);

        textPanel.setVisible(true);
    }


    class SaveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List.addElement(field.getText());
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
