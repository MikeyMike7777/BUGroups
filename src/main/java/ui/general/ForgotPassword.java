package ui.general;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPassword extends JPanel {
    ForgotPassword(Dimension preferredSize){
        super();
        preferredSize.setSize(preferredSize.getWidth(),
                preferredSize.getHeight() - 30);
        setPreferredSize(preferredSize);
        initForgot();
        setBackground(Color.LIGHT_GRAY);
    }
    private void initForgot(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Forgot Password"));
        panel.add(addEmail());
        panel.add(addReset());
        panel.add(addBack());
        panel.setAlignmentX(LEFT_ALIGNMENT);
        add(panel);
    }

    private Component addEmail(){
        JPanel email = new JPanel();
        email.setLayout(new BoxLayout(email, BoxLayout.X_AXIS));
        email.add(new JLabel("Email: "));
        email.add(new JTextField(40));
        email.setAlignmentX(CENTER_ALIGNMENT);
        return email;
    }
    private Component addReset(){
        JButton reset = new JButton("Reset VIA Email");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel temp = (JPanel)getParent();
                setVisible(false);
                temp.remove(0);
                //send reset email
                back();
            }
        });
        reset.setAlignmentX(CENTER_ALIGNMENT);
        return reset;
    }
    private Component addBack(){
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel temp = (JPanel)getParent();
                setVisible(false);
                temp.remove(0);
                back();
            }
        });
        back.setAlignmentX(CENTER_ALIGNMENT);
        return back;
    }
    private void back(){
        add(new Login(getPreferredSize()));
    }
}
