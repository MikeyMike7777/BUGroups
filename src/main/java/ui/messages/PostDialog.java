package ui.messages;

import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostDialog extends JDialog {
    JPanel panel;
    JTextPane message;
    JTextField course;
    MessageBoardPage parent;

    PostDialog(Component parent, Component board, String title) {
        super(SwingUtilities.windowForComponent(parent));
        this.parent = (MessageBoardPage)board;
        createAndDisplay(title);
    }

    void createAndDisplay(String title) {
        setMinimumSize(new Dimension(400, 300));
        setTitle(title);
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
        course = new JTextField(20);
        course.setToolTipText("Course Number");
        message = new JTextPane();
        message.setMaximumSize(new Dimension(350, 600));
        message.setToolTipText("Message");
        scrolls.add(message, Component.CENTER_ALIGNMENT);
        panel.add(course, Component.CENTER_ALIGNMENT);
        panel.add(scrolls, Component.CENTER_ALIGNMENT);
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
            Window.controller.createMessage(
                    message.getText(), Window.username, course.getText(),
                    parent.id, "null"
            );
            parent.refresh();

            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
