package ui.messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyDialog extends JDialog {
    Message parent;
    Message dialog;
    JPanel panel;
    JTextPane reply;

    ReplyDialog(Message owner, Message dialog) {
        super(SwingUtilities.windowForComponent(owner));
        parent = owner;
        this.dialog = dialog;
        createAndDisplay();
    }

    void createAndDisplay() {
        setPreferredSize(new Dimension(400, 350));
        setTitle("New Reply");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(400, 350));

        addComponents();
        addButtons();

        add(panel);
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    void addComponents() {
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setPreferredSize(new Dimension(250, 200));
        reply = new JTextPane();
        reply.setPreferredSize(new Dimension(250, 600));
        scrolls.add(reply, Component.CENTER_ALIGNMENT);
        panel.add(scrolls, Component.CENTER_ALIGNMENT);
    }

    void addButtons() {
        JButton post = new JButton();
        post.setText("Post");
        post.setAlignmentX(Component.CENTER_ALIGNMENT);
        post.addActionListener(new ReplyDialog.PostActionListener());

        JButton close = new JButton("Close");
        close.setAlignmentX(Component.CENTER_ALIGNMENT);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(post);
        panel.add(close);
    }

    class PostActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.replies.add(new Message(reply.getText()));
            MessageDialog t = (MessageDialog)dialog.getRootPane().getParent();
            t.repaint(0);
            parent.repaint();
            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
