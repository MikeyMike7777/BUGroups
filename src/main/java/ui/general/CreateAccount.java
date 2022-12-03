package ui.general;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.general.Window;

public class CreateAccount extends JPanel {

    CreateAccount(Dimension preferredSize){
        super();
        preferredSize.setSize(preferredSize.getWidth(),
                preferredSize.getHeight() - 30);
        setPreferredSize(preferredSize);
        initCreate();
        setBackground(Color.LIGHT_GRAY);
    }

    private void initCreate(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Create Account");
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(addFirstName());
        panel.add(addLastName());
        panel.add(addPhone());
        panel.add(addEmail());
        panel.add(addCreateAccount());
        panel.add(addBack());
        panel.setAlignmentX(LEFT_ALIGNMENT);
        add(panel);
    }

    private Component addFirstName(){
        JPanel first = new JPanel();
        first.setLayout(new BoxLayout(first, BoxLayout.X_AXIS));
        first.add(new JLabel("First Name: "));
        first.add(new JTextField(15));
        first.setAlignmentX(CENTER_ALIGNMENT);
        return first;
    }

    private Component addLastName(){
        JPanel last = new JPanel();
        last.setLayout(new BoxLayout(last, BoxLayout.X_AXIS));
        last.add(new JLabel("Last Name: "));
        last.add(new JTextField(15));
        last.setAlignmentX(CENTER_ALIGNMENT);
        return last;
    }

    private Component addPhone(){
        JPanel email = new JPanel();
        email.setLayout(new BoxLayout(email, BoxLayout.X_AXIS));
        email.add(new JLabel("Phone: "));
        email.add(new JTextField(40));
        email.setAlignmentX(CENTER_ALIGNMENT);
        return email;
    }

    private Component addEmail(){
        JPanel email = new JPanel();
        email.setLayout(new BoxLayout(email, BoxLayout.X_AXIS));
        email.add(new JLabel("Email: "));
        email.add(new JTextField(40));
        email.setAlignmentX(CENTER_ALIGNMENT);
        return email;
    }

    private Component addCreateAccount(){
        JButton back = new JButton("Create Account");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CreateAccount.this.getRootPane()
                    .getParent(), "Temporary password sent to email address!",
                        "Confirmation", JOptionPane.QUESTION_MESSAGE);
                //Window.controller.createStudent();
                JPanel temp = (JPanel)getParent();
                setVisible(false);
                temp.remove(0);
                //Create Account
                temp.add(new Login(getPreferredSize()));
            }
        });
        back.setAlignmentX(CENTER_ALIGNMENT);
        return back;
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
