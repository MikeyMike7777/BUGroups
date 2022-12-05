package ui.profile;

import database.utils.BUGUtils;
import ui.profile.ProfilePage;
import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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

    void setText(){
        name.setText("Name:");
        email.setText("Email:");
        email.setEditable(false);
        phoneNumber.setText("Phone #:");
    }
    void createAndDisplay() {
        setLayout(new GridLayout(2,2));

        setText();

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
        cancel.addActionListener(new CancelActionListener());

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        buttonPanel.setVisible(true);
    }
    class CancelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    class SaveInfoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Vector<String> s = new Vector<>();
            try {
                s = (Vector<String>) ProfilePage.info.elementAt(3);
            } catch(ArrayIndexOutOfBoundsException n){
                s = null;
            }
            BUGUtils.controller.createProfileInfo(Window.username, name.getText(), email.getText(), phoneNumber.getText(), s);
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
