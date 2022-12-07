package ui.messages;

import database.utils.BUGUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.stream.Collectors;

public class MessageDialog extends JDialog {
    MessageBox messageBox;
    MessageBox parent;
    JPanel panel;

    MessageDialog(MessageBox owner) {
        super();
        parent = owner;
        messageBox = new MessageBox(owner);
        messageBox.setFocusable(false);
        createAndDisplay();
    }

    void createAndDisplay() {
        setPreferredSize(new Dimension(750, 500));
        setTitle("View Message");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(750, 500));

        addComponents();
        addButtons();

        add(panel);
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    void addComponents() {
        if (messageBox.getMouseListeners().length > 0)
            messageBox.removeMouseListener(messageBox.getMouseListeners()[0]);
        panel.add(messageBox);
        panel.add(new JLabel(" "));

        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(new Dimension(750, 250));
        scrolls.add(addMessages(), Component.CENTER_ALIGNMENT);
        panel.add(scrolls, Component.CENTER_ALIGNMENT);
    }

    Component addMessages() {
        JPanel messages = new JPanel();
        messages.setLayout(new BoxLayout(messages, BoxLayout.Y_AXIS));
        messages.setSize(new Dimension(730, 400));
        messages.setMaximumSize(new Dimension(730, 8000));
        Vector<MessageBox> replies = messageBox.replies;
        for (MessageBox m : replies) {
            m.setAlignmentX(Component.CENTER_ALIGNMENT);
            messages.add(m);
            messages.add(new JLabel(" "));
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
            PostDialog reply = new ReplyDialog(parent, messageBox,
                    (MessageUtil)parent.getParent().getParent().getParent().getParent(),
                    "New Reply", (MessageDialog)panel.getTopLevelAncestor(), MessageDialog.this);
        }
    }

    void refresh(MessageBox dialog) {
        panel.setVisible(false);

        panel.removeAll();
        parent = new MessageBox(dialog);
        messageBox = parent;
        messageBox.setFocusable(false);
        addComponents();
        addButtons();

        panel.setVisible(true);
        panel.validate();
        getRootPane().validate();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
