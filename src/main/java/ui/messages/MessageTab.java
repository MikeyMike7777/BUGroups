package ui.messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MessageTab extends JPanel {
    JPanel messageHeader;
    JButton post;
    JMenuBar bar;
    JMenu select;
    MessageBoardPage board;
    static final String[] names = {
            "Biology and Health Sciences",
            "Business",
            "Chemistry and Biochemistry",
            "Education and Social Work",
            "Engineering and Computer Science",
            "English and Journalism",
            "Fine Arts",
            "Foreign Languages",
            "Geology and Environmental Science",
            "History and Political Science",
            "Math and Physics",
            "Philosophy and BIC",
            "Psychology and Sociology"
    };

    HashMap<String, Integer> boardKeys = new HashMap<>();

    MessageTab(Dimension d) {
        super();
        setSize(d);
        for (int i = 0; i < names.length; ++i)
            boardKeys.put(names[i], i);
        createAndDisplay();
    }

    void createAndDisplay() {
        setAlignmentX(LEFT_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addBoardMenu());
        add(panel);
    }

    Component addBoardMenu() {
        messageHeader = new JPanel();
        messageHeader.setLayout(new BoxLayout(messageHeader, BoxLayout.X_AXIS));
        bar = new JMenuBar();
        bar.setPreferredSize(new Dimension(260, 30));
        select = new JMenu();
        select.setText("Select a Message Board");
        select.setAlignmentX(CENTER_ALIGNMENT);
        select.setPreferredSize(new Dimension(260, 30));
        JMenuItem[] boards = createBoardOptions();
        for (int i = 0; i < names.length; ++i)
            select.add(boards[i]);
        bar.add(select);
        messageHeader.add(bar);
        messageHeader.add(addPostButton());
        return messageHeader;
    }

    JButton addPostButton() {
        post = new JButton("Post");
        post.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (board != null) {
                    PostDialog post = new PostDialog(board, board, "New Post");
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(post.getRootPane().getParent(),
                            "No board selected!", "Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        return post;
    }

    JMenuItem[] createBoardOptions() {
        JMenuItem[] items = new JMenuItem[names.length];
        for (int i = 0; i < names.length; ++i) {
            items[i] = new JMenuItem(names[i]);
            items[i].addActionListener(new MenuActionListener());
        }
        return items;
    }

    class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = ((JMenuItem)e.getSource()).getText();
            ((JMenu)((JPopupMenu)((JMenuItem)e.getSource()).getParent()).
                    getInvoker()).setText(s);
            if (board != null)
                getParent().remove(board);
            getParent().validate();
            board = new MessageBoardPage(s, boardKeys.get(s));
            getParent().add(board);
        }
    }

    public JMenu getMessageBoardMenu(){
        return select;
    }
    public String[] getMessageBoardMenuItemNames(){
        return names;
    }
}
