package ui.messages;

import ui.profile.MyMessages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class MessageBox extends JPanel {

    String name;
    String message;
    Boolean isReply;
    String courseNum;
    String id;
    String date;
    Integer boardId;
    SpringLayout layout = new SpringLayout();
    Vector<MessageBox> replies = new Vector<>();
    static SimpleDateFormat f = new SimpleDateFormat("M/d/yyyy h:mm a");

    MessageBox(MessageBox template) {
        this.name = template.name;
        this.message = template.message;
        this.isReply = template.isReply;
        this.replies = template.replies;
        this.id = template.id;
        this.courseNum = template.courseNum;
        this.date = template.date;
        this.boardId = template.boardId;

        setLayout(layout);
        setPreferredSize(new Dimension(630, 300));
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
        this.isReply = !v.elementAt(2).equals("null");
        this.id = (String)v.elementAt(3);
        for (Object m : (Vector<Object>)v.elementAt(4))
            this.replies.add(new MessageBox((Vector<Object>)m));
        this.courseNum = (String)v.elementAt(5);
        this.date = f.format(v.elementAt(6));
        this.boardId = (Integer)v.elementAt(7);

        setLayout(layout);
        setPreferredSize(new Dimension(630, 300));
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
        setAlignmentX(CENTER_ALIGNMENT);
        JLabel user = new JLabel(name);
        add(user);
        layout.putConstraint(SpringLayout.WEST, user, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, user, 5, SpringLayout.NORTH, this);

        JLabel time = new JLabel(date);
        add(time);
        layout.putConstraint(SpringLayout.EAST, time, -20, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, time, 5, SpringLayout.NORTH, this);

        JLabel course = new JLabel(courseNum);
        add(course, LEFT_ALIGNMENT);
        layout.putConstraint(SpringLayout.WEST, course, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, course, 25, SpringLayout.NORTH, this);

        JLabel label = new JLabel("<html>" + message + "</html>");
        label.setPreferredSize(new Dimension(630, 100));
        add(label, LEFT_ALIGNMENT);
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, label, 35, SpringLayout.NORTH, this);

        if (!isReply) {
            JLabel replies = new JLabel("Replies: " + this.replies.size());
            add(replies);
            layout.putConstraint(SpringLayout.WEST, replies, 5, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.SOUTH, replies, -5, SpringLayout.SOUTH, this);
        }
    }

    public String getText() {
        return message;
    }

    public String getCourse() {
        return courseNum;
    }

    public String getId() {
        return id;
    }
    public Integer getBoardId() {
        return boardId;
    }

    public void addListeners() {
        if (getMouseListeners().length > 0)
            removeMouseListener(getMouseListeners()[0]);
        addMouseListener(new ClickListener());
        addFocusListener(new MessageFocusListener());
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

    class ClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {
            requestFocusInWindow(true);
            ((MyMessages)getRootPane().getParent()).focused = MessageBox.this;
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
