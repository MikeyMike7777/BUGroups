package ui.tutors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TutoringOffer extends JPanel {
    String name; // of tutor-- eventually will be its own class but this is for UI purposes
    String classTutoring;
    String professor;
    String semester;
    String hourlyRate;

    TutoringOffer(String n, String c, String p, String s, String hr){
        name = n;
        classTutoring = c;
        professor = p;
        semester = s;
        hourlyRate = hr;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(500, 100));
        setMaximumSize(new Dimension(500, 300));
        setBackground(Color.white);
        setOpaque(true);
        setFocusable(true);
        add(new JLabel(n));
        addMouseListener(new TutoringOffer.TutoringOfferClickListener());
        addFocusListener(new TutoringOffer.TutoringOfferFocusListener());
    }

    @Override
    public String getName() {
        return name;
    }

    public String getClassTutoring() {
        return classTutoring;
    }

    public String getProfessor() {
        return professor;
    }

    public String getSemester() {
        return semester;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    class TutoringOfferClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {
            TutorDialog info;
            if (hasFocus()) {
                info = new TutorDialog(TutoringOffer.this);
                getParent().requestFocusInWindow();
            }
            else requestFocusInWindow(true);
        }
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    class TutoringOfferFocusListener implements FocusListener {
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
