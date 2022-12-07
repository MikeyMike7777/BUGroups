package ui.messages;

import database.utils.BUGUtils;
import ui.general.Window;
import ui.profile.MyMessages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PostDialog extends JDialog {
    JPanel panel;
    JTextPane message;
    JTextField course;
    MessageUtil parent;
    String tempText;
    String tempCourse;
    Integer boardId;

    PostDialog(Component parent, MessageUtil board, String title) {
        super();
        this.parent = board;
        createAndDisplay(title);
    }

    public PostDialog(String text, String course, String title,
                      Integer boardId, MessageUtil window) {
        super();
        this.tempText = text;
        this.tempCourse = course;
        this.boardId = boardId;
        this.parent = window;
        createAndDisplay(title);
    }

    void createAndDisplay(String title) {
        setPreferredSize(new Dimension(400, 300));
        setTitle(title);
        setResizable(false);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(400, 300));

        addComponents();
        addButtons();

        add(panel);
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    void addComponents() {
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setMaximumSize(new Dimension(350, 500));
        scrolls.setPreferredSize(new Dimension(350, 400));
        JPanel temp = new JPanel();
        temp.add(new JLabel("Course Number:"));
        course = new JTextField(20);
        if (tempCourse != null)
            course.setText(tempCourse);
        temp.add(course);
        message = new JTextPane();
        message.setPreferredSize(new Dimension(330, 800));
        message.setToolTipText("Message");
        if (tempText != null)
            message.setText(tempText);
        scrolls.add(message, Component.CENTER_ALIGNMENT);
        panel.add(temp);
        panel.add(scrolls);
    }

    void addButtons() {
        JButton post = new JButton();
        post.setText("Post");
        post.setAlignmentX(Component.CENTER_ALIGNMENT);
        post.addActionListener(new PostDialog.PostActionListener());

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
            if (course.getText().matches("[a-zA-Z]{3} ?[1-4][1-5][0-9]{2}")) {
                if (message.getText().length() < 1024) {
                    String temp = course.getText().toUpperCase();
                    String course;
                    if (temp.charAt(3) != ' ' && temp.charAt(4) != ' ')
                        course = temp.split("[1-5][1-5][0-9]{2}")[0] + ' ' + temp.split("[a-zA-Z]{3,4}")[1];
                    else course = PostDialog.this.course.getText();
                    BUGUtils.controller.createMessage(
                            message.getText(), Window.username, course,
                            parent.getIndex(), "null"
                    );
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

    @Override
    public void dispose() {
        super.dispose();
    }
}
