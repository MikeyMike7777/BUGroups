package ui.messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class MessageBox extends JPanel {
    static int counter = 0;

    String name;
    String message;
    Boolean isReply;
    String courseNum;
    Vector<MessageBox> replies = new Vector<>();

    MessageBox(MessageBox template) {
        this.name = template.name;
        this.message = template.message;
        this.isReply = template.isReply;
        this.replies = template.replies;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(700, 100));
        setMaximumSize(new Dimension(700, 500));
        setBackground(Color.white);
        setOpaque(true);
        setFocusable(true);
        if (!isReply) {
            addMouseListener(new MessageClickListener());
            addFocusListener(new MessageFocusListener());
        }
        createMessage();
    }

    public MessageBox(Vector<Object> v) {
        this.name = (String)v.elementAt(0);
        this.message = (String)v.elementAt(1);
        this.isReply = v.elementAt(2) == null;
        for (Object m : (Vector<Object>)v.elementAt(3))
            this.replies.add(new MessageBox((Vector<Object>)m));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(700, 100));
        setMaximumSize(new Dimension(700, 500));
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
        label.setPreferredSize(new Dimension(650, 50));
        setAlignmentX(CENTER_ALIGNMENT);
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
                messageDialog = new MessageDialog(MessageBox.this);
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
