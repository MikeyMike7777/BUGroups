package ui.messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyDialog extends JDialog {
    MessageBox parent;
    MessageBox dialog;
    JPanel panel;
    JTextPane reply;

    ReplyDialog(MessageBox owner, MessageBox dialog) {
        super(SwingUtilities.windowForComponent(owner));
        parent = owner;
        this.dialog = dialog;
        createAndDisplay();
    }

    void createAndDisplay() {
        setMinimumSize(new Dimension(400, 300));
        setTitle("New Reply");
        setResizable(false);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(400, 300));

        addComponents();
        addButtons();

        add(panel);
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    void addComponents() {
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setMaximumSize(new Dimension(350, 200));
        reply = new JTextPane();
        reply.setMaximumSize(new Dimension(350, 600));
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
            //parent.replies.add(new MessageBox());
            MessageDialog t = (MessageDialog)dialog.getRootPane().getParent();
            t.repaint(0);
            JPanel board = (JPanel)parent.getParent().getParent().getParent().getParent().getParent();
            // delete and redraw board pulling from database again?
            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
