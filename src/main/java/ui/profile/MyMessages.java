package ui.profile;

import database.utils.BUGUtils;
import ui.messages.MessageBox;
import ui.messages.MessageUtil;
import ui.messages.PostDialog;
import ui.messages.RepostDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.stream.Collectors;


public class MyMessages extends JDialog implements MessageUtil {
    JPanel panel;
    public MessageBox focused;
    int id = 0;

    MyMessages() {
        super();
        this.createAndDisplay();
    }

    public void createAndDisplay() {
        setPreferredSize(new Dimension(750, 500));
        setTitle("My Messages");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(750, 500));

        addComponents();
        addActions();

        add(panel);
        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    void addComponents() {
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(new Dimension(750, 250));
        scrolls.add(addMessages(), Component.CENTER_ALIGNMENT);
        panel.add(scrolls, Component.CENTER_ALIGNMENT);
    }

    Component addMessages() {
        JPanel messages = new JPanel();
        messages.setLayout(new BoxLayout(messages, BoxLayout.Y_AXIS));
        messages.setSize(new Dimension(730, 400));
        messages.setMaximumSize(new Dimension(730, 8000));
        Vector<MessageBox> fetched = new Vector<>(BUGUtils.controller.fetchMessages().stream()
                .map(v -> new MessageBox((Vector<Object>)v)).collect(Collectors.toList()));
        for (MessageBox m : fetched) {
            m.setAlignmentX(Component.CENTER_ALIGNMENT);
            m.addListeners();
            messages.add(m);
            messages.add(new JLabel(" "));
        }
        messages.getComponent(0).requestFocus();
        focused = (MessageBox)messages.getComponent(0);
        return messages;
    }

    @Override
    public int getIndex() {
        return id;
    }

    void addActions() {
        JButton repost = new JButton();
        repost.setText("Repost");
        repost.setAlignmentX(Component.CENTER_ALIGNMENT);
        repost.addActionListener(new RepostActionListener());

        JButton delete = new JButton();
        delete.setText("Delete");
        delete.setAlignmentX(Component.CENTER_ALIGNMENT);
        delete.addActionListener(new DeleteActionListener());

        JButton close = new JButton("Close");
        close.setAlignmentX(Component.CENTER_ALIGNMENT);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(repost);
        panel.add(delete);
        panel.add(close);
    }

    class RepostActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PostDialog edit;
            MessageBox temp = focused;
            if (temp != null && isFocused()) {
                id = temp.getBoardId();
                edit = new RepostDialog(temp.getText(), temp.getCourse(),
                        "Edit and Repost Message", id,
                        (MessageUtil)(getRootPane().getParent()), temp.getId());
            } else JOptionPane.showMessageDialog(getRootPane().getParent(),
                    "No message selected!",
                    "Error", JOptionPane.WARNING_MESSAGE);
            refresh();
        }
    }

    class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isFocused()) {
                MessageBox temp = focused;
                if (JOptionPane.showOptionDialog(getRootPane().getParent(),
                        "Are you sure you want to delete?",
                        "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, new String[]{"Yes", "No"}, "Yes") == 0)
                    BUGUtils.controller.deleteMessage(temp.getId());
            } else JOptionPane.showMessageDialog(getRootPane().getParent(),
                    "No message selected!",
                    "Error", JOptionPane.WARNING_MESSAGE);

            refresh();
        }
    }

    @Override
    public void refresh() {
        panel.setVisible(false);

        panel.removeAll();
        addComponents();
        addActions();

        panel.setVisible(true);
        panel.validate();
        getRootPane().validate();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
