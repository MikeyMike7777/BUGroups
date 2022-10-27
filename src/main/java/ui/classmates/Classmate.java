package ui.classmates;

import ui.messages.Message;
import ui.messages.MessageDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Classmate extends JPanel {
    String name;

    private static final String[] names = {
            "Carsyn Smeda",
            "Bryce Robinson",
            "Mikey Mathews",
            "Gabriel Goulis",
            "Carson Buntin"
    };

    Classmate(String n){
        name = n;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(500, 100));
        setMaximumSize(new Dimension(500, 300));
        setBackground(Color.white);
        setOpaque(true);
        setFocusable(true);
        add(new JLabel(n));
    }

    public String getName(){
        return name;
    }

    class MessageClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {
            ClassmateInfoDialog info;
            if (hasFocus()) {
                info = new ClassmateInfoDialog(Classmate.this);
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
