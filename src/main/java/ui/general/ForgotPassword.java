package ui.general;

import database.utils.BUGUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgotPassword extends JPanel {
    JTextField emailField;

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
        JLabel label = new JLabel("Forgot Password");
        emailField = new JTextField(40);
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label, CENTER_ALIGNMENT);
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
        email.add(emailField);
        email.setAlignmentX(CENTER_ALIGNMENT);
        return email;
    }
    private Component addReset(){
        JButton reset = new JButton("Get Temporary Password");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ForgotPassword.this.getRootPane()
                    .getParent(), "Email sent!", "Confirmation",
                        JOptionPane.QUESTION_MESSAGE);
                JPanel temp = (JPanel)getParent();
                setVisible(false);
                temp.remove(0);
                //send reset email
                if(BUGUtils.controller.verifyAccount(emailField.getText())){
                    BUGUtils.controller.sendPasswordReset(emailField.getText());
                }
                temp.add(new Login(getPreferredSize()));
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
                temp.add(new Login(getPreferredSize()));
            }
        });
        back.setAlignmentX(CENTER_ALIGNMENT);
        return back;
    }
}
