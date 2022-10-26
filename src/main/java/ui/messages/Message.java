package ui.messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Message extends JPanel {
    static int counter = 0;

    private static final String[] names = {
            "Carsyn Smeda",
            "Bryce Robinson",
            "Mikey Mathews",
            "Gabriel Goulis",
            "Carson Buntin"
    };

    private static final String[] messages = {
            "Hi everyone! I'm looking for a tutor in Databases but don't " +
                    "see any listed. Has anyone taken it who could help me " +
                    "out one or two days a week?",
            "Howdy, does anyone have an opinion on which professor to take " +
                    "for software 1? I've heard Cerny is difficult but that" +
                    " you learn more with him than with the other options.",
            "Is anyone taking SPA 1310 with someone other than Spinks? None " +
                    "of my classmates are on here and I desperately need a " +
                    "study group lol",
            "Yeah I'm in the same boat for 1320. Spinks is difficult but if " +
                    "you study the vocab and practice saying stuff out loud " +
                    "you should do fine.",
            "Yo everyone and their mother is tutoring for geology, how am I " +
                    "supposed to pick a good one? Not trying to fail my easy" +
                    " science credits haha"
    };

    String name;
    String message;
    Boolean isReply;
    Vector<Message> replies = new Vector<>();

    Message(Boolean reply) {
        isReply = reply;
        name = names[counter];
        message = messages[counter];
        if (counter == 2) {
            ++counter;
            replies.add(new Message(true));
        } else ++counter;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(500, 100));
        setMaximumSize(new Dimension(500, 300));
        setBackground(Color.white);
        setOpaque(true);
        setFocusable(true);
        if (!isReply) {
            addMouseListener(new MessageClickListener());
            addFocusListener(new MessageFocusListener());
        }
        createMessage();
    }

    Message(Message m) {
        name = m.name;
        isReply = m.isReply;
        message = m.message;
        replies = m.replies;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(500, 100));
        setMaximumSize(new Dimension(500, 300));
        setBackground(Color.white);
        setOpaque(true);
        setFocusable(true);
        if (!isReply) {
            addMouseListener(new MessageClickListener());
            addFocusListener(new MessageFocusListener());
        }
        createMessage();
    }

    void createMessage() {
        JLabel user = new JLabel(name);
        JLabel label = new JLabel("<html>" + message + "</html>");
        label.setPreferredSize(new Dimension(450, 50));
        add(user, LEFT_ALIGNMENT);
        add(new JLabel(" "), LEFT_ALIGNMENT);
        add(label, LEFT_ALIGNMENT);
        if (!isReply)
            add(new JLabel("Replies: " + replies.size()));
        add(new JLabel(" "), LEFT_ALIGNMENT);
    }

    class MessageClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {
            MessageDialog messageDialog;
            if (hasFocus()) {
                messageDialog = new MessageDialog(Message.this);
                getParent().requestFocusInWindow();
            }
            else requestFocusInWindow(true);
        }
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    class MessageFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            setBackground(Color.blue);
            for (Component c : getComponents())
                if (c.getClass().equals(JLabel.class))
                    ((JLabel)c).setForeground(Color.white);
        }

        @Override
        public void focusLost(FocusEvent e) {
            setBackground(Color.white);
            for (Component c : getComponents())
                if (c.getClass().equals(JLabel.class))
                    ((JLabel)c).setForeground(Color.black);
        }
    }
}
