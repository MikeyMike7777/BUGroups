import javax.swing.*;
import java.awt.*;

public class MessageBoard extends JPanel {
    Integer iD;
    String name;

    MessageBoard() {
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
        panel.add(addMessages());
        scrolls.add(panel);
        add(scrolls);
    }

    Component addMessages() {
        JPanel message = new JPanel();
        message.setBackground(Color.white);
        message.setMinimumSize(new Dimension(500, 100));
        message.setLayout(new BoxLayout(message, BoxLayout.Y_AXIS));
        message.add(new JLabel("Carsyn Smeda"));
        message.add(new JLabel("Hi everyone! I'm looking for a tutor in " +
                "Databases but don't see any listed.\nHas anyone taken it who " +
                "could help me out one or two days a week?"));
        return message;
    }
}
