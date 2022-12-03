package ui.messages;

import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplyDialog extends PostDialog {
    MessageBox repliesTo;
    MessageBox dialog;

    ReplyDialog(MessageBox owner, MessageBox dialog, Component board, String title) {
        super(owner, board, title);
        repliesTo = owner;
        this.dialog = dialog;
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
            Window.controller.createMessage(
                    message.getText(), Window.username, course.getText(),
                    parent.id, repliesTo.id
            );

            parent.refresh();

            dispose();
        }
    }
}
