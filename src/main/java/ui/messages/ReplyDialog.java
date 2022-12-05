package ui.messages;

import database.utils.BUGUtils;
import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyDialog extends PostDialog {
    MessageBox repliesTo;
    MessageBox dialog;
    MessageDialog window;

    ReplyDialog(MessageBox owner, MessageBox dialog, Component board,
                String title, MessageDialog window) {
        super(owner, board, title);
        repliesTo = owner;
        this.dialog = dialog;
        this.window = window;
        course.setText(dialog.courseNum);
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
            dialog = new MessageBox(BUGUtils.controller.createMessage(
                    message.getText(), Window.username, course.getText(),
                    parent.getX(), repliesTo.id
            ));
            repliesTo.replies.add(dialog);
            parent.repaint(4);
            window.refresh(repliesTo);

            dispose();
        }
    }
}
