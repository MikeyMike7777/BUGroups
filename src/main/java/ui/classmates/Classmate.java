package ui.classmates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

public class Classmate extends JPanel {
    String name;
    String email;
    String phone;
    String availability;

    Classmate(ArrayList<String> info){
        name = info.get(0);
        email = info.get(1);
        phone = info.get(2);
        availability = info.get(3); // FIXME: this is where the error is-- out of bounds
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(500, 100));
        setMaximumSize(new Dimension(500, 300));
        setBackground(Color.white);
        setOpaque(true);
        setFocusable(true);
        add(new JLabel(name));
        addMouseListener(new Classmate.ClassmateClickListener());
        addFocusListener(new Classmate.ClassmateFocusListener());
    }

    @Override
    public String getName() { return name; }
    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }
    public String getAvailability() { return availability; }

    class ClassmateClickListener implements MouseListener {
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

    class ClassmateFocusListener implements FocusListener {
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
