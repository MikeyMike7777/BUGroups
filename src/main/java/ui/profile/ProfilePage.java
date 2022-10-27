package ui.profile;

import javax.swing.*;
import java.awt.*;

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
        add(userInfo);

        buildAvalibilityInfo();
        add(availibilty);

        add(classList);

        add(tutorList);
    }

    void buildUserInfoBox(){
        JTextArea textArea = new JTextArea();
        JLabel textHeader = new JLabel("My Profile: ");

        userInfo.setLayout(new GridLayout(2,1));
        userInfo.add(textHeader);

        textArea.setEditable(false);
        textArea.setText("""
                Carsyn Smeda
                carsyn_smeda1@baylor.edu
                254-555-9762""");
        textArea.setVisible(true);

        userInfo.add(textArea);
        userInfo.setSize(new Dimension(225, 145));
        userInfo.setVisible(true);
    }

    void buildProfileButtons(){
        JButton logout = new JButton("Logout");
        JButton edit = new JButton("Edit Profile Info");
        JButton tutor = new JButton("Tutor a Class");
        JButton messages = new JButton("Sent Messages");


        profileButtons.setLayout(new GridLayout(4, 1));
        profileButtons.add(logout);
        profileButtons.add(edit);
        profileButtons.add(tutor);
        profileButtons.add(messages);

        profileButtons.setVisible(true);
    }

    void buildAvalibilityInfo(){
        JTextArea infoText = new JTextArea();
        JLabel infoLabel = new JLabel("Availability: ");

        availibilty.setLayout(new GridLayout(2, 1));
        availibilty.add(infoLabel);

        infoText.setEditable(false);
        infoText.setText("""
                M:  12:00-5:00
                T:  3:00-4:50
                W:  2:15-3:30
                TH: 4:45-6:15
                F:  5:00-7:30""");
        infoText.setVisible(true);

        availibilty.add(infoText);
        availibilty.setVisible(true);
    }

    void setLayout(){
        SpringLayout springLayout = new SpringLayout();

        //Put the springLayout constraints
        springLayout.putConstraint(SpringLayout.WEST, userInfo, 20, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, userInfo, 20, SpringLayout.NORTH, this);

        springLayout.putConstraint(SpringLayout.EAST, availibilty, -50, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.NORTH, availibilty, 10, SpringLayout.NORTH, this);

        springLayout.putConstraint(SpringLayout.WEST, classList, 20, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, classList, -10, SpringLayout.SOUTH, this);

        springLayout.putConstraint(SpringLayout.EAST, profileButtons, -40, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, profileButtons, -40, SpringLayout.SOUTH, this);

        setLayout(springLayout);
    }

}
