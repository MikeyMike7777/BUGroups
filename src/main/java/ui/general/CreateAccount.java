package ui.general;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import database.utils.BUGUtils;
import ui.general.Window;
import ui.profile.ProfilePage;

public class CreateAccount extends JPanel {
    JTextField firstName, lastName, emailField, number;
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
        firstName = new JTextField(15);
        lastName = new JTextField(15);
        emailField = new JTextField(40);
        number = new JTextField(40);

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
        first.add(firstName);
        first.setAlignmentX(CENTER_ALIGNMENT);
        return first;
    }

    private Component addLastName(){
        JPanel last = new JPanel();
        last.setLayout(new BoxLayout(last, BoxLayout.X_AXIS));
        last.add(new JLabel("Last Name: "));
        last.add(lastName);
        last.setAlignmentX(CENTER_ALIGNMENT);
        return last;
    }

    private Component addPhone(){
        JPanel email = new JPanel();
        email.setLayout(new BoxLayout(email, BoxLayout.X_AXIS));
        email.add(new JLabel("Phone: "));
        email.add(number);
        email.setAlignmentX(CENTER_ALIGNMENT);
        return email;
    }

    private Component addEmail(){
        JPanel email = new JPanel();
        email.setLayout(new BoxLayout(email, BoxLayout.X_AXIS));
        email.add(new JLabel("Email: "));
        email.add(emailField);
        email.setAlignmentX(CENTER_ALIGNMENT);
        return email;
    }

    private Component addCreateAccount(){
        JButton back = new JButton("Create Account");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check first name, last name, number, email
                if(firstName.getText().length() < 1) {
                    JOptionPane.showMessageDialog(getRootPane().getParent(),
                            "Please enter a valid first name",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(lastName.getText().length() < 1) {
                    JOptionPane.showMessageDialog(getRootPane().getParent(),
                            "Please enter a valid last name",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(number.getText().length() < 10) {
                    JOptionPane.showMessageDialog(getRootPane().getParent(),
                            "Please enter a valid phone number",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    try {
                        //test if number is digits
                        Long.parseLong(number.getText());

                        if(emailField.getText().endsWith("@baylor.edu")) {
                            //verify account
                            if(!(BUGUtils.controller.verifyAccount(emailField.getText()))){
                                JOptionPane.showMessageDialog(CreateAccount.this.getRootPane()
                                                .getParent(), "Temporary password sent to email address! (Email may take up to five minutes to arrive)",
                                        "Confirmation", JOptionPane.QUESTION_MESSAGE);
                                String name = firstName.getText() + " " + lastName.getText();
                                String password = BUGUtils.controller.generatePassword(8);
                                BUGUtils.controller.sendRegisterEmail(emailField.getText()
                                        .toLowerCase(), password, name);
                                String username = emailField.getText().toLowerCase()
                                        .substring(0, emailField.getText().length() - 11);

                                BUGUtils.controller.registerStudent(username, password, name, number.getText(),
                                        emailField.getText().toLowerCase());
                                BUGUtils.controller.createProfileInfo(username, name,
                                        emailField.getText().toLowerCase(), number.getText(), null);

                                //go back to home page
                                JPanel temp = (JPanel)getParent();
                                setVisible(false);
                                temp.remove(0);
                                //Create Account
                                temp.add(new Login(getPreferredSize()));
                            }
                            else {
                                JOptionPane.showMessageDialog(getRootPane().getParent(),
                                        "An account is already associated with this email",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(getRootPane().getParent(),
                                    "Invalid email",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException nan){
                        JOptionPane.showMessageDialog(getRootPane().getParent(),
                                "Please only use numbers in \"Phone\"",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
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
