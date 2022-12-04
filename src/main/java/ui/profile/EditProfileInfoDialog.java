package ui.profile;

import ui.general.Window;
import ui.profile.ProfilePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfileInfoDialog extends JDialog {

    JLabel nameLabel;

    JPanel ogPanel;

    JLabel emailLabel;

    JLabel phoneLabel;

    JPanel textPanel = new JPanel();

    JPanel buttonPanel = new JPanel();

    JTextField name = new JTextField(20);

    JTextField email = new JTextField(20);

    JTextField phoneNumber = new JTextField(20);


    EditProfileInfoDialog(JPanel panel) {
        super();

        ogPanel = panel;
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

        save.addActionListener(new SaveInfoActionListener());

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        buttonPanel.setVisible(true);
    }

    class SaveInfoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Window.controller.createProfileInfo(Window.username, name.getText(), email.getText(), phoneNumber.getText(), null);
            ProfilePage.repaintUserInfo();
            dispose();
        }
    }

    void buildTextPanel(){
        nameLabel = new JLabel("Enter Name:");
        emailLabel = new JLabel("Enter Email:");
        phoneLabel = new JLabel("Enter Phone #:");
        textPanel.add(nameLabel);
        textPanel.add(name);
        textPanel.add(emailLabel);
        textPanel.add(email);
        textPanel.add(phoneLabel);
        textPanel.add(phoneNumber);

        textPanel.setVisible(true);
    }


}
