package ui.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

import database.utils.BUGUtils;
import ui.general.Window;

public class ProfilePage extends JPanel {

    JPanel userInfo = new JPanel();

    JPanel availibilty = new JPanel();

    JPanel classList = new ProfileClassList();

    JPanel tutorList = new ProfileTutorList();


    Vector<Object> info = new Vector<>(4);

    static Vector<String> times = new Vector<>(7);

    ProfilePage me = this;

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
        buildEditButton("View My Messages");
        add(userInfo);

        buildAvalibilityInfo();
        buildEditButton("Edit Availability");
        add(availibilty);

        add(classList);

        add(tutorList);
    }

    void buildUserInfoBox(){
        JTextArea textArea = new JTextArea();
        JLabel textHeader = new JLabel("My Profile: ");


        userInfo.setLayout(new GridLayout(3,1));
        userInfo.add(textHeader);

        info = BUGUtils.controller.fetchProfileInfo(Window.username);

        textArea.setEditable(false);
        if(info.size() == 0 || info.elementAt(0) == null) {
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

    void buildEditButton(String s){
        JPanel button = new JPanel();
        JButton editInfo = new JButton(s);


        if(Objects.equals(s, "View My Messages")) {
            editInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyMessages myMessages = new MyMessages();
                }
            });
        } else if(Objects.equals(s, "Edit Availability")){
            editInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new EditAvailabilityDialog(info, me);
                    repaintAvailInfo();
                }
            });
        }

        button.add(editInfo);


        if(Objects.equals(s, "View My Messages")) {
            userInfo.add(button);
        } else if (Objects.equals(s, "Edit Availability")) {
            availibilty.add(button);
        }
    }

    void repaintUserInfo(){
        userInfo.setVisible(false);
        userInfo.removeAll();
        buildEditButton("View My Messages:");
        buildUserInfoBox();
    }

    void repaintAvailInfo(){
        availibilty.setVisible(false);
        availibilty.removeAll();
        buildAvalibilityInfo();
        buildEditButton("Edit Availability:");
    }

    void buildAvalibilityInfo(){
        JTextArea infoText = new JTextArea();
        JLabel infoLabel = new JLabel("Availability: ");

        availibilty.setLayout(new GridLayout(3, 1));
        availibilty.add(infoLabel);

        infoText.setEditable(false);

        info = BUGUtils.controller.fetchProfileInfo(Window.username);

        if(info.size() > 0) {
            try {
                times = (Vector<String>) info.elementAt(3);
            } catch (NullPointerException e) {
            }
        } else{
        }

        if(times == null || times.size() == 0) {
            infoText.setText("""
                    Click "Edit Availability" to add your availability!""");
        } else {
            infoText.setText(times.elementAt(0) + "\t\t" + times.elementAt(5) + "\n" + times.elementAt(1)
                    + "\t\t" + times.elementAt(6) + "\n"+  times.elementAt(2) + "\n" + times.elementAt(3)
                    + "\n" + times.elementAt(4));
        }
        infoText.setVisible(true);

        availibilty.add(infoText);
        availibilty.setVisible(true);
    }

}
