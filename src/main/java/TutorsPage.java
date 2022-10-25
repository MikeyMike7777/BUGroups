import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* not sure if this works (i don't remember how we're doing things with the
Window class), really just copied and pasted Bryce's work but theoretically
it should do similar things */

public class TutorsPage extends JPanel{
    JLabel mainHeader;
    Integer iD;
    String name;

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
        mainHeader = new JLabel("TUTORS");
        add(mainHeader);

        // scroll bar
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(500, 100);

        JPanel dropdown = new TutorsTab();
        add(dropdown);
    }

}
