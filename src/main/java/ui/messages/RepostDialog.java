package ui.messages;

import database.utils.BUGUtils;
import ui.profile.MyMessages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepostDialog extends PostDialog {

    String id;
    public RepostDialog(String text, String course, String title, Integer boardId,
                        MyMessages window, String id) {
        super(text, course, title, boardId, window);
        this.id = id;
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
            BUGUtils.controller.editRepostMessage(id, message.getText());
            if (parent != null)
                parent.repaint(4);

            dispose();
        }
    }

}
