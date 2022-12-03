package ui.messages;

import message.Message;
import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class MessageBoardPage extends JPanel {
    Integer iD;
    String name;

    MessageBoardPage() {
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
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setMinimumSize(new Dimension(750, 450));
        component.setMaximumSize(new Dimension(750, 9000));

        Collection<Message> messages = Window.controller.fetchBoard(iD);
        MessageBox messageBox;
        for (Message m : messages) {
            messageBox = new MessageBox(m);
            if (!messageBox.isReply)
                component.add(messageBox);
        }

        return component;
    }

    public void setCurrentMessageBoard(){

    }
}
