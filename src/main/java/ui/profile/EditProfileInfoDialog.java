package ui.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfileInfoDialog extends JDialog {

    JLabel nameLabel;

    JLabel emailLabel;

    JLabel phoneLabel;

    JPanel textPanel = new JPanel();

    JPanel buttonPanel = new JPanel();

    JTextField classCode = new JTextField(20);

    JTextField section = new JTextField(20);

    JTextField professor = new JTextField(20);


    EditProfileInfoDialog() {
        super();

        setSize(250,350);
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

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        buttonPanel.setVisible(true);
    }

    void buildTextPanel(){
        nameLabel = new JLabel("Enter Name:");
        emailLabel = new JLabel("Enter Email:");
        phoneLabel = new JLabel("Enter Phone #:");
        textPanel.add(nameLabel);
        textPanel.add(classCode);
        textPanel.add(emailLabel);
        textPanel.add(section);
        textPanel.add(phoneLabel);
        textPanel.add(professor);

        textPanel.setVisible(true);
    }


}
