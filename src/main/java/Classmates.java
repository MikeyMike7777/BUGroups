import javax.swing.*;
import java.awt.*;

import static java.awt.Component.CENTER_ALIGNMENT;

public class Classmates extends JPanel {
    JLabel mainHeader;

    Classmates() {
        super();
        createAndDisplay();
    }

    void createAndDisplay() {
        setMinimumSize(new Dimension(600, 350));
        setAlignmentX(CENTER_ALIGNMENT);
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        // header label
        mainHeader = new JLabel("CLASSMATES");
        add(mainHeader);

        // scroll bar
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(500, 100);

        JPanel dropdown = new ClassmatesTab();
        add(dropdown);
    }
}
