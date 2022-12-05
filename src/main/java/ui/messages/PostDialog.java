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
    Component parent;
    String tempText;
    String tempCourse;
    Integer boardId;

    PostDialog(Component parent, Component board, String title) {
        super();
        this.parent = board;
        createAndDisplay(title);
    }

    public PostDialog(String text, String course, String title,
                      Integer boardId, MyMessages window) {
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
            Vector<Object> v = BUGUtils.controller.createMessage(
                    message.getText(), Window.username, course.getText(),
                    parent.getX(), "null"
            );
            if (parent != null)
                parent.repaint(4);

            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
