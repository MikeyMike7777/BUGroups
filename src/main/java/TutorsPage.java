import javax.swing.*;
import java.awt.*;

public class TutorsPage extends JPanel{
    JLabel mainHeader;

    TutorsPage() {
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
        mainHeader = new JLabel("Class:");
        add(mainHeader);

        // scroll bar
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(500, 100);

        JPanel dropdown = new TutorsTab();
        add(dropdown);
    }

}
