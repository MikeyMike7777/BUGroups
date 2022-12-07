package ui.messages;

import database.utils.BUGUtils;
import ui.general.Window;
import ui.profile.MyMessages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepostDialog extends PostDialog {

    String id;
    public RepostDialog(String text, String course, String title, Integer boardId,
                        MessageUtil window, String id) {
        super(text, course, title, boardId, window);
        this.id = id;
        this.course.setEditable(false);
    }

    void addButtons() {
        JButton post = new JButton();
        post.setText("Post");
        post.setAlignmentX(Component.CENTER_ALIGNMENT);
        post.addActionListener(new RepostActionListener());

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

    class RepostActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (course.getText().matches("[a-zA-Z]{3} ?[1-4][1-5][0-9]{2}")) {
                if (message.getText().length() < 1024) {
                    BUGUtils.controller.editRepostMessage(id, message.getText());
                    if (parent != null)
                        parent.refresh();
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
