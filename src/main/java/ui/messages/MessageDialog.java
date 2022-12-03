package ui.messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class MessageDialog extends JDialog {
    MessageBox messageBox;
    MessageBox parent;
    JPanel panel;
    MessageDialog(MessageBox owner) {
        super(SwingUtilities.windowForComponent(owner));
        parent = owner;
        messageBox = new MessageBox(owner);
        messageBox.setFocusable(false);
        createAndDisplay();
    }

    void createAndDisplay() {
        setPreferredSize(new Dimension(750, 450));
        setTitle("View Message");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(750, 450));

        messageBox.removeMouseListener(messageBox.getMouseListeners()[0]);
        messageBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(messageBox);
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
        scrolls.setPreferredSize(new Dimension(750, 100));
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.setMinimumSize(new Dimension(750, 300));
        pane.setMaximumSize(new Dimension(750, 8000));
        pane.add(addMessages(), Component.CENTER_ALIGNMENT);
        scrolls.add(pane, Component.CENTER_ALIGNMENT);
        panel.add(scrolls, Component.CENTER_ALIGNMENT);
    }

    Component addMessages() {
        JPanel messages = new JPanel();
        messages.setLayout(new BoxLayout(messages, BoxLayout.Y_AXIS));
        messages.setMinimumSize(new Dimension(750, 300));
        messages.setMaximumSize(new Dimension(750, 8000));
        Vector<MessageBox> replies = messageBox.replies;
        for (MessageBox m : replies) {
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
            ReplyDialog reply = new ReplyDialog(parent, messageBox);
            repaint();
        }
    }

    @Override
    public void repaint(long tm) {
        MessageDialog newDialog = new MessageDialog(parent);
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
