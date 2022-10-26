package ui.messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class MessageDialog extends JDialog {

    Message message;
    Message parent;
    JPanel panel;
    MessageDialog(Message owner) {
        super(SwingUtilities.windowForComponent(owner));
        parent = owner;
        message = new Message(parent);
        message.setFocusable(false);
        createAndDisplay();
    }

    void createAndDisplay() {
        setPreferredSize(new Dimension(600, 450));
        setTitle("View ui.messages.Message");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(600, 450));

        message.removeMouseListener(message.getMouseListeners()[0]);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(message);
        panel.add(new JLabel(" "));
        addComponents();
        addButtons();

        add(panel);
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    void addComponents() {
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setPreferredSize(new Dimension(600, 100));
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.setMinimumSize(new Dimension(600, 300));
        pane.setMaximumSize(new Dimension(600, 8000));
        pane.add(addMessages(), Component.CENTER_ALIGNMENT);
        scrolls.add(pane, Component.CENTER_ALIGNMENT);
        panel.add(scrolls, Component.CENTER_ALIGNMENT);
    }

    Component addMessages() {
        JPanel messages = new JPanel();
        messages.setLayout(new BoxLayout(messages, BoxLayout.Y_AXIS));
        messages.setMinimumSize(new Dimension(600, 300));
        messages.setMaximumSize(new Dimension(600, 8000));
        Vector<Message> replies = message.replies;
        for (Message m : replies) {
            m.setAlignmentX(Component.CENTER_ALIGNMENT);
            messages.add(m);
        }
        return messages;
    }

    void addButtons() {
        JButton reply = new JButton();
        reply.setText("Reply");
        reply.setAlignmentX(Component.CENTER_ALIGNMENT);
        reply.addActionListener(new ReplyActionListener());

        JButton close = new JButton("Close");
        close.setAlignmentX(Component.CENTER_ALIGNMENT);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(reply);
        panel.add(close);
    }

    class ReplyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
