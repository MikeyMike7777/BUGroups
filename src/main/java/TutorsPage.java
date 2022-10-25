import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* not sure if this works (i don't remember how we're doing things with the
Window class), really just copied and pasted Bryce's work but theoretically
it should do similar things */

public class TutorsPage extends JPanel{
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
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(500, 100);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addTutors());
        scrolls.add(panel);
        add(scrolls);
    }

    Component addTutors() {
        JPanel message = new JPanel();
        message.setBackground(Color.white);
        message.setMinimumSize(new Dimension(500, 100));
        message.setLayout(new BoxLayout(message, BoxLayout.Y_AXIS));
        message.add(new JLabel("Carsyn Smeda"));
        return message;
    }
}
