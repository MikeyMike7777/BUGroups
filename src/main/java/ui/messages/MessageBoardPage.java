package ui.messages;

import database.utils.BUGUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MessageBoardPage extends JPanel {
    public int id = 0;
    String name;

    MessageBoardPage(String name, Integer id) {
        super();
        this.name = name;
        this.id = id;
        this.createAndDisplay();
    }

    void createAndDisplay() {
        setPreferredSize(new Dimension(750, 470));
        setAlignmentX(CENTER_ALIGNMENT);
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(new Dimension(750, 470));
        scrolls.add(addMessages());
        scrolls.doLayout();
        add(scrolls);
    }

    Component addMessages() {
        JPanel component = new JPanel();
        component.setLayout(new BoxLayout(component, BoxLayout.Y_AXIS));
        component.setSize(new Dimension(720, 400));
        component.setMaximumSize(new Dimension(720, 9000));

        Vector<Object> messages = BUGUtils.controller.fetchBoard(id);
        MessageBox messageBox;
        for (Object v : messages) {
            messageBox = new MessageBox((Vector<Object>)v);
            if (!messageBox.isReply) {
                component.add(messageBox);
                component.add(new JLabel(" "));
            }
        }
        component.doLayout();
        return component;
    }

    @Override
    public int getX() {
        return id;
    }

    @Override
    public void repaint(long l) {
        setVisible(false);
        removeAll();
        addComponents();
        setVisible(true);
        if (this.getRootPane() != null)
            getRootPane().validate();
    }
}
