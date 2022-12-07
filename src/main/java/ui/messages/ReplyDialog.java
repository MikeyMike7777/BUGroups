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
    JDialog messageDialog;

    ReplyDialog(MessageBox owner, MessageBox dialog, MessageUtil board,
                String title, MessageDialog window, JDialog parent) {
        super(owner, board, title);
        repliesTo = owner;
        this.dialog = dialog;
        this.window = window;
        this.messageDialog = parent;
        this.course.setEditable(false);
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
            if (course.getText().matches("[a-zA-Z]{3,4} ?[1-4][1-5][0-9]{2}")) {
                if (message.getText().length() < 1024) {
                    dialog = new MessageBox(BUGUtils.controller.createMessage(
                            message.getText(), Window.username, course.getText(),
                            parent.getIndex(), repliesTo.id
                    ));
                    repliesTo.replies.add(dialog);
                    parent.refresh();
                    window.refresh(repliesTo);
                    messageDialog.dispose();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(getRootPane().getParent(),
                            "Message too long.",
                            null, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(getRootPane().getParent(),
                        "Please enter a valid course code.",
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
