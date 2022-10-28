package ui.messages;

import ui.general.CreateAccount;
import ui.messages.Message;

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
        setMinimumSize(new Dimension(750, 550));
        setMaximumSize(new Dimension(750, 1200));
        setAlignmentX(CENTER_ALIGNMENT);
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(750, 450);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(750, 450));
        panel.setMaximumSize(new Dimension(750, 9000));
        panel.add(addMessages());
        scrolls.add(panel);
        add(scrolls);
    }

    Component addMessages() {
        Message message;
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setMinimumSize(new Dimension(750, 450));
        component.setMaximumSize(new Dimension(750, 9000));
        for (int i = 0; i < 4; ++i) {
            message = new Message(false);
            if (!message.isReply)
                component.add(message);
        }
        Message.counter = 0;
        return component;
    }

    public void setCurrentMessageBoard(){

    }
}
