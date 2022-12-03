package ui.messages;

import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyDialog extends PostDialog {
    MessageBox parent;
    MessageBox dialog;
    JPanel panel;
    JTextPane reply;

    ReplyDialog(MessageBox owner, MessageBox dialog) {
        super(owner);
        parent = owner;
        this.dialog = dialog;
        createAndDisplay("New Reply");
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
//            Window.controller.createMessage(dialog.message, dialog.name,
//                    dialog.courseNum);
//            parent.replies.add(new MessageBox());
            MessageDialog t = (MessageDialog) dialog.getRootPane().getParent();
            t.repaint(0);
            JPanel board = (JPanel) parent.getParent().getParent().getParent().getParent().getParent();
            // delete and redraw board pulling from database again?
            dispose();
        }
    }
}
