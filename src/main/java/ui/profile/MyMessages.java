package ui.profile;

import database.utils.BUGUtils;
import ui.general.CreateAccount;
import ui.messages.MessageBox;
import ui.messages.PostDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.stream.Collectors;

public class MyMessages extends JDialog {
    JPanel panel;

    MyMessages() {
        super();
        createAndDisplay();
    }

    void createAndDisplay() {
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
        return messages;
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
            if (isFocused())
                edit = new PostDialog(((MessageBox)getFocusOwner()).getText(),
                        ((MessageBox)getFocusOwner()).getCourse(), "Repost");
            else JOptionPane.showMessageDialog(getRootPane().getParent(),
                    "No message selected!",
                    "Error", JOptionPane.WARNING_MESSAGE);
            refresh();
        }
    }

    class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isFocused())
                if (JOptionPane.showOptionDialog(getRootPane().getParent(),
                        "No message selected!",
                        "Error", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, new String[]{"Yes", "No"}, "Yes") == 1)
                    BUGUtils.controller.deleteMessage(((MessageBox)getParent()).getId());
            else JOptionPane.showMessageDialog(getRootPane().getParent(),
                    "No message selected!",
                    "Error", JOptionPane.WARNING_MESSAGE);

            refresh();
        }
    }

    void refresh() {
        panel.setVisible(false);

        panel.removeAll();
        addComponents();
        addComponents();

        panel.setVisible(true);
        panel.validate();
        getRootPane().validate();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
