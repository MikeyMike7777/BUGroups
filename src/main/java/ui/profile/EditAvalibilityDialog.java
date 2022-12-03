package ui.profile;

import javax.swing.*;
import java.awt.*;

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

    void createAndDisplay() {
        setLayout(new GridLayout(2,2));

        buildTextPanel();
        add(textPanel);

        buildSaveCancel();
        add(buttonPanel);

        setVisible(true);
    }

    void buildSaveCancel(){
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        buttonPanel.setVisible(true);
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
