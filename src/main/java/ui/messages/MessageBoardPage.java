package ui.messages;

import database.utils.BUGUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MessageBoardPage extends JPanel {
    Integer id;
    String name;

    MessageBoardPage(String name, Integer id) {
        super();
        this.name = name;
        this.id = id;
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

        Vector<Object> messages = BUGUtils.controller.fetchBoard(id);
        MessageBox messageBox;
        for (Object v : messages) {
            messageBox = new MessageBox((Vector<Object>)v);
            if (!messageBox.isReply)
                component.add(messageBox);
        }

        return component;
    }

    void refresh() {
        setVisible(false);
        removeAll();
        addComponents();
        setVisible(true);
        getRootPane().validate();
    }
}
