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
        setMaximumSize(new Dimension(600, 800));
        setAlignmentX(CENTER_ALIGNMENT);
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(520, 250);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(600, 300));
        panel.setMaximumSize(new Dimension(600, 8000));
        panel.add(addMessages());
        scrolls.add(panel);
        add(scrolls);
    }

    Component addMessages() {
        Message message;
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setMinimumSize(new Dimension(600, 300));
        component.setMaximumSize(new Dimension(600, 8000));
        for (int i = 0; i < 4; ++i) {
            message = new Message(false);
            if (!message.isReply)
                component.add(message);
        }
        Message.counter = 0;
        return component;
    }
}
