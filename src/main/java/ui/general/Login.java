package ui.general;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Vector;

import database.utils.BUGUtils;


public class Login extends JPanel {

    JPanel username;

    JPasswordField password = new JPasswordField(15);
    public Login(Dimension d) {
        super();
        createAndDisplay(d);
    }

    private void createAndDisplay(Dimension d) {
        setPreferredSize(d);
        setAlignmentX(LEFT_ALIGNMENT);
        addComponents();
        setVisible(true);
    }

    private void addComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addLabel());
        panel.add(addUsername());
        panel.add(addPassword());
        panel.add(addLogin());
        panel.add(addForgot());
        panel.add(addCreate());
        panel.setAlignmentX(LEFT_ALIGNMENT);
        add(panel);
    }

    private Component addLabel() {
        JLabel windowLabel = new JLabel("Login");
        windowLabel.setAlignmentX(CENTER_ALIGNMENT);
        return windowLabel;
    }

    private Component addUsername() {
        username = new JPanel();
        username.setLayout(new BoxLayout(username, BoxLayout.X_AXIS));
        username.add(new JLabel("Username: "));
        username.add(new JTextField(15));
        username.setAlignmentX(CENTER_ALIGNMENT);
        return username;
    }

    private Component addPassword() {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        passwordPanel.add(new JLabel("Password: "));
        passwordPanel.add(password);
        passwordPanel.setAlignmentX(CENTER_ALIGNMENT);
        return passwordPanel;
    }

    private Component addLogin() {
        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Object> s = BUGUtils.controller.fetchStudent(Window.username);


                if(s.size() == 0){
                    int action = JOptionPane.showConfirmDialog(getRootPane().getParent(),
                            "Username Not Found!",
                            null, JOptionPane.CANCEL_OPTION);
                } else {
                    String user = (String) s.elementAt(0);
                    String userPassword = (String) s.elementAt(1);

                    if(!Objects.equals(password.getPassword().toString(), userPassword)){
                        int action = JOptionPane.showConfirmDialog(getRootPane().getParent(),
                                "Incorrect Password!",
                                null, JOptionPane.CANCEL_OPTION);
                    }

                    JPanel temp = (JPanel) getParent();
                    setVisible(false);
                    temp.remove(0);
                    temp.add(new Window(getPreferredSize(),
                            ((JTextField) username.getComponent(1)).getText()));
                }
            }
        });
        login.setAlignmentX(CENTER_ALIGNMENT);
        return login;
    }

    private Component addForgot() {
        JButton forgotPassword = new JButton("Forgot Password?");
        forgotPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel temp = (JPanel)getParent();
                setVisible(false);
                temp.remove(0);
                temp.add(new ForgotPassword(getPreferredSize()));
            }
        });
        forgotPassword.setAlignmentX(CENTER_ALIGNMENT);
        return forgotPassword;
    }

    private Component addCreate() {
        JButton createAccount = new JButton("Create Account");
        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel temp = (JPanel)getParent();
                setVisible(false);
                temp.remove(0);
                temp.add(new CreateAccount(getPreferredSize()));
            }
        });
        createAccount.setAlignmentX(CENTER_ALIGNMENT);
        return createAccount;
    }
}
