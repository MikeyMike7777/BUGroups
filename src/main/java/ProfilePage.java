import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JPanel {

    JTextPane textArea = new JTextPane();

    ProfilePage() {
        super();
        createAndDisplay();
    }

    void createAndDisplay() {
        setMinimumSize(new Dimension(600, 200));
        setAlignmentX(CENTER_ALIGNMENT);
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        buildStudentInfoBox();
        add(textArea);

        add(new ClassList());

    }

    void buildStudentInfoBox(){
        textArea.setEditable(false);

        textArea.setText("""
                Profile:\s
                Carsyn Smeda
                carsyn_smeda1@baylor.edu
                254-555-9762""");
        textArea.setSize(100, 200);
        textArea.add(new JLabel("My Profile"));
    }




}
