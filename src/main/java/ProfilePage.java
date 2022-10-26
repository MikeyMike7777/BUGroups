import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JPanel {

    JPanel userInfo = new JPanel();

    JPanel profileButtons = new JPanel();

    SpringLayout layout;

    ProfilePage(Dimension d) {
        super();
        setPreferredSize(d);
        createAndDisplay();
    }

    void createAndDisplay() {
        setAlignmentX(CENTER_ALIGNMENT);
        //setLayout(layout);
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        buildUserInfoBox();
        add(userInfo);

        add(new ClassList());

        buildProfileButtons();
        add(profileButtons);
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
        userInfo.setVisible(true);
    }

    void buildProfileButtons(){
        JButton logout = new JButton("LogOut");
        JButton edit = new JButton("Edit Profile Info");
        JButton tutor = new JButton("Tutor A Class");
        JButton messages = new JButton("Sent Messages");

        profileButtons.setLayout(new GridLayout(4, 1));
        profileButtons.add(logout);
        profileButtons.add(edit);
        profileButtons.add(tutor);
        profileButtons.add(messages);

        profileButtons.setVisible(true);
    }

    public void setUpLayout(){
        //layout.putConstraint(textArea, SwingConstants.NORTH_EAST, 4, );

    }

}
