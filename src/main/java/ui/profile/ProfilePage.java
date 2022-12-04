package ui.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;
import ui.general.Window;

public class ProfilePage extends JPanel {

    static JPanel userInfo = new JPanel();

    static JPanel availibilty = new JPanel();

    JPanel classList = new ProfileClassList();

    JPanel tutorList = new ProfileTutorList();


    static Vector<Object> info = new Vector<>(4);

    static Vector<String> times = new Vector<>(7);

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

    static void buildUserInfoBox(){
        JTextArea textArea = new JTextArea();
        JLabel textHeader = new JLabel("My Profile: ");


        userInfo.setLayout(new GridLayout(3,1));
        userInfo.add(textHeader);

        info = Window.controller.fetchProfileInfo(Window.username);

        textArea.setEditable(false);
        if(info.size() == 0) {
            textArea.setText("""
                    Click "Edit Profile Info" to add your name, email, and phone
                     number!""");
        } else {
            textArea.setText(info.get(0) + "\n" + info.get(1) + "\n" + info.get(2) + "\n");
        }


        textArea.setVisible(true);
        userInfo.add(textArea);
        userInfo.setSize(new Dimension(225, 145));
        userInfo.setVisible(true);
    }

    static void buildEditButton(String s){
        JPanel button = new JPanel();
        JButton editInfo = new JButton(s);


        if(Objects.equals(s, "Edit Profile Info:")) {
            editInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new EditProfileInfoDialog(userInfo);
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

    static void repaintUserInfo(){
        userInfo.setVisible(false);
        userInfo.removeAll();
        buildUserInfoBox();
        buildEditButton("Edit Profile Info:");
    }

    static void repaintAvailInfo(){
        availibilty.setVisible(false);
        availibilty.removeAll();
        buildAvalibilityInfo();
        buildEditButton("Edit Availability:");
    }

    static void buildAvalibilityInfo(){
        JTextArea infoText = new JTextArea();
        JLabel infoLabel = new JLabel("Availability: ");

        availibilty.setLayout(new GridLayout(3, 1));
        availibilty.add(infoLabel);

        infoText.setEditable(false);

        info = Window.controller.fetchProfileInfo(Window.username);

        if(info.size() > 0) {
            try {
                times = (Vector<String>) info.elementAt(3);
            } catch (NullPointerException e) {
                times = null;
            }
        } else{
            times = null;
        }

        if(times == null || times.size() == 0) {
            infoText.setText("""
                    Click "Edit Availability" to add your availability!""");
        } else {
            infoText.setText(times.elementAt(0) + "\n" + times.elementAt(1) + "\n" + times.elementAt(2)
                    + "\n" + times.elementAt(3) + "\n"+  times.elementAt(4) + "\n" + times.elementAt(5)
                    + "\n" + times.elementAt(6) + "\n" );
        }
        infoText.setVisible(true);

        availibilty.add(infoText);
        availibilty.setVisible(true);
    }

}
