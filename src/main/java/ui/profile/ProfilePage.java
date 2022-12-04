package ui.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ProfilePage extends JPanel {

    JPanel userInfo = new JPanel();

    JPanel profileButtons = new JPanel();

    JPanel availibilty = new JPanel();

    JPanel classList = new ProfileClassList();

    JPanel tutorList = new ProfileTutorList();




    public ProfilePage(Dimension d) {
        super();
        setPreferredSize(d);
        createAndDisplay();
    }

    void createAndDisplay() {
        setAlignmentX(CENTER_ALIGNMENT);
        //setLayout();
        setLayout(new GridLayout(2, 2));
        addComponents();
        setVisible(true);
    }

    void addComponents() {

        buildUserInfoBox();
        buildEditButton("Edit Profile Info:");
        add(userInfo);

        buildAvalibilityInfo();
        buildEditButton("Edit Availability:");
        add(availibilty);

        add(classList);

        add(tutorList);
    }

    void buildUserInfoBox(){
        JTextArea textArea = new JTextArea();
        JLabel textHeader = new JLabel("My Profile: ");

        userInfo.setLayout(new GridLayout(3,1));
        userInfo.add(textHeader);


        textArea.setEditable(false);
        textArea.setText("""
                Click "Edit Profile Info" to add your name, email, and phone
                 number!""");
        textArea.setVisible(true);

        userInfo.add(textArea);
        userInfo.setSize(new Dimension(225, 145));
        userInfo.setVisible(true);
    }

    void buildEditButton(String s){
        JPanel button = new JPanel();
        JButton editInfo = new JButton(s);


        if(Objects.equals(s, "Edit Profile Info:")) {
            editInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new EditProfileInfoDialog();
                }
            });
        } else if(Objects.equals(s, "Edit Availability:")){
            editInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new EditAvalibilityDialog();
                }
            });
        }

        button.add(editInfo);


        if(Objects.equals(s, "Edit Profile Info:")) {
            userInfo.add(button);
        } else if (Objects.equals(s, "Edit Availability:")) {
            availibilty.add(button);
        }
    }

    void buildAvalibilityInfo(){
        JTextArea infoText = new JTextArea();
        JLabel infoLabel = new JLabel("Availability: ");

        availibilty.setLayout(new GridLayout(3, 1));
        availibilty.add(infoLabel);

        infoText.setEditable(false);
        infoText.setText("""
                Click "Edit Availability" to add your availability!""");
        infoText.setVisible(true);

        availibilty.add(infoText);
        availibilty.setVisible(true);
    }

}
