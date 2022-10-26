package ui.profile;

import javax.swing.*;
import java.awt.*;

public class ProfileClassList extends JPanel {

    String[] classes = {
            "CSI 3336 - Systems Programming",
            "CSI 3471 - Software Engineering I",
            "WGS 2300 - Women and Gender Studies",
            "GEO 1306 - The Dynamic Earth"
    };

    JLabel header;

    JList<String> classList;

    JPanel buttons = new JPanel();

    ProfileClassList(){
        super();
        GridLayout grid = new GridLayout(3, 1);
        setLayout(grid);
        createAndDisplay();
    }

    void createAndDisplay() {
        setMaximumSize(new Dimension(100,100));
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        // header label
        header = new JLabel("Current Classes:");
        add(header);


        classList = new JList<>(classes);
        add(classList);

        buildAddRemoveButtons();
        add(buttons);

    }

    public void buildAddRemoveButtons(){
        JButton add = new JButton("Add Class");
        JButton remove = new JButton("Remove Class");


        buttons.add(add);
        buttons.add(remove);

        buttons.setSize(new Dimension(10, 20));
        buttons.setVisible(true);
    }


}
