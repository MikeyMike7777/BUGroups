package ui.profile;

import database.utils.BUGUtils;
import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class EditAvailabilityDialog extends JDialog{

    JLabel mLabel;

    JLabel tLabel;

    JLabel wLabel;

    JLabel thLabel;

    JLabel fLabel;

    JLabel satLabel;

    JLabel sunLabel;

    JPanel textPanel = new JPanel();

    JPanel buttonPanel = new JPanel();

    JTextField m = new JTextField(20);

    JTextField t = new JTextField(20);

    JTextField w = new JTextField(20);
    JTextField thu = new JTextField(20);
    JTextField fri = new JTextField(20);
    JTextField sat = new JTextField(20);
    JTextField sun = new JTextField(20);
    Vector<Object> info;

    ProfilePage panel;
    JPanel base;


    EditAvailabilityDialog(Vector<Object> info, ProfilePage panel) {
        super();
        this.info = info;
        this.panel = panel;
        setTitle("Update Availability");
        base = new JPanel();
        setPreferredSize(new Dimension(400,300));
        createAndDisplay();
    }

    void setTexts(){
        try {
            m.setText(ProfilePage.times.elementAt(0).replace("Mon: ", ""));
            t.setText(ProfilePage.times.elementAt(1).replace("Tue: ", ""));
            w.setText(ProfilePage.times.elementAt(2).replace("Wed: ", ""));
            thu.setText(ProfilePage.times.elementAt(3).replace("Thu: ", ""));
            fri.setText(ProfilePage.times.elementAt(4).replace("Fri: ", ""));
            sat.setText(ProfilePage.times.elementAt(5).replace("Sat: ", ""));
            sun.setText(ProfilePage.times.elementAt(6).replace("Sun: ", ""));
        } catch (ArrayIndexOutOfBoundsException n){
            m.setText("");
            t.setText("");
            w.setText("");
            thu.setText("");
            fri.setText("");
            sat.setText("");
            sun.setText("");
        }
    }
    void createAndDisplay() {
        base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
        base.setPreferredSize(new Dimension(400, 300));

        setTexts();
        initTexts();

        buildTextPanel();
        base.add(textPanel);

        buildSaveCancel();
        base.add(buttonPanel);

        add(base);
        pack();
        setVisible(true);
    }

    private void initTexts() {
        m.setMaximumSize(new Dimension(300, 25));
        m.setAlignmentX(Component.CENTER_ALIGNMENT);
        t.setMaximumSize(new Dimension(300, 25));
        t.setAlignmentX(Component.CENTER_ALIGNMENT);
        w.setMaximumSize(new Dimension(300, 25));
        w.setAlignmentX(Component.CENTER_ALIGNMENT);
        thu.setMaximumSize(new Dimension(300, 25));
        thu.setAlignmentX(Component.CENTER_ALIGNMENT);
        fri.setMaximumSize(new Dimension(300, 25));
        fri.setAlignmentX(Component.CENTER_ALIGNMENT);
        sat.setMaximumSize(new Dimension(300, 25));
        sat.setAlignmentX(Component.CENTER_ALIGNMENT);
        sun.setMaximumSize(new Dimension(300, 25));
        sun.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    void buildSaveCancel(){
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setMaximumSize(new Dimension(360, 70));
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        save.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        cancel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        buttonPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        cancel.addActionListener(new CancelActionListener());
        save.addActionListener(new SaveAvailActionListener());

        buttonPanel.add(save);
        buttonPanel.add(cancel);
    }

    class CancelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    class SaveAvailActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Vector<String> times = new Vector<>();
            times.add("Mon: " + m.getText());
            times.add("Tue: " + t.getText());
            times.add("Wed: " + w.getText());
            times.add("Thu: " + thu.getText());
            times.add("Fri: " + fri.getText());
            times.add("Sat: " + sat.getText());
            times.add("Sun: " + sun.getText());

            BUGUtils.controller.changeAvail(Window.username, times);
            panel.repaintAvailInfo();
            dispose();
        }
    }
    void buildTextPanel(){
        mLabel =   new JLabel("Monday:      ");
        tLabel =   new JLabel("Tuesday:     ");
        wLabel =   new JLabel("Wednesday: ");
        thLabel =  new JLabel("Thursday:    ");
        fLabel =   new JLabel("Friday:         ");
        satLabel = new JLabel("Saturday:     ");
        sunLabel = new JLabel("Sunday:       ");

        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setPreferredSize(new Dimension(360, 260));
        addDay(mLabel, m);
        addDay(tLabel, t);
        addDay(wLabel, w);
        addDay(thLabel, thu);
        addDay(fLabel, fri);
        addDay(satLabel, sat);
        addDay(sunLabel, sun);
    }

    private void addDay(JLabel label, JTextField in) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(label);
        p.add(in);
        textPanel.add(p);
    }
}
