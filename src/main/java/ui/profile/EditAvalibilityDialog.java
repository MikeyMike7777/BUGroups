package ui.profile;

import ui.general.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class EditAvalibilityDialog extends JDialog{

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


    EditAvalibilityDialog() {
        super();

        setSize(250,700);
        createAndDisplay();
    }

    void setTexts(){
        m.setText("Mon:");
        t.setText("Tue:");
        w.setText("Wed:");
        thu.setText("Thu:");
        fri.setText("Fri:");
        sat.setText("Sat:");
        sun.setText("Sun:");
    }
    void createAndDisplay() {
        setLayout(new GridLayout(2,2));

        setTexts();

        buildTextPanel();
        add(textPanel);

        buildSaveCancel();
        add(buttonPanel);

        setVisible(true);
    }

    void buildSaveCancel(){
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");

        save.addActionListener(new SaveAvailActionListener());

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        buttonPanel.setVisible(true);
    }

    class SaveAvailActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Vector<String> times = new Vector<>();
            times.add(m.getText());
            times.add(t.getText());
            times.add(w.getText());
            times.add(thu.getText());
            times.add(fri.getText());
            times.add(sat.getText());
            times.add(sun.getText());

            if(ProfilePage.info.size() != 0) {
                ui.general.Window.controller.createProfileInfo(Window.username, (String) ProfilePage.info.elementAt(0),
                        (String) ProfilePage.info.elementAt(1),
                        (String) ProfilePage.info.elementAt(2), times);
            } else {
                ui.general.Window.controller.createProfileInfo(Window.username, null, null, null, times);
            }

            ProfilePage.repaintAvailInfo();
            dispose();
        }
    }
    void buildTextPanel(){
        mLabel = new JLabel("Monday:");
        tLabel = new JLabel("Tuesday:");
        wLabel = new JLabel("Wednesday:");
        thLabel = new JLabel("Thursday:");
        fLabel = new JLabel("Friday:");
        satLabel = new JLabel("Saturday:");
        sunLabel = new JLabel("Sunday:");

        textPanel.add(mLabel);
        textPanel.add(m);
        textPanel.add(tLabel);
        textPanel.add(t);
        textPanel.add(wLabel);
        textPanel.add(w);
        textPanel.add(thLabel);
        textPanel.add(thu);
        textPanel.add(fLabel);
        textPanel.add(fri);
        textPanel.add(satLabel);
        textPanel.add(sat);
        textPanel.add(sunLabel);
        textPanel.add(sun);

        textPanel.setVisible(true);
    }
}
