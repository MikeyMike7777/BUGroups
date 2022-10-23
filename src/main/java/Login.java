import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JPanel {
    Login() {
        super();
        createAndDisplay();
    }

    private void createAndDisplay() {
        setSize(new Dimension(600, 400));
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
        JPanel username = new JPanel();
        username.setLayout(new BoxLayout(username, BoxLayout.X_AXIS));
        username.add(new JLabel("Username: "));
        username.add(new JTextField(15));
        username.setAlignmentX(CENTER_ALIGNMENT);
        return username;
    }

    private Component addPassword() {
        JPanel password = new JPanel();
        password.setLayout(new BoxLayout(password, BoxLayout.X_AXIS));
        password.add(new JLabel("Password: "));
        password.add(new JPasswordField(15));
        password.setAlignmentX(CENTER_ALIGNMENT);
        return password;
    }

    private Component addLogin() {
        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel temp = (JPanel)getParent();
                setVisible(false);
                temp.remove(0);
                temp.add(new Window());
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
                getParent().setVisible(false);
                getParent().remove(0);
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
                getParent().setVisible(false);
                getParent().remove(0);
            }
        });
        createAccount.setAlignmentX(CENTER_ALIGNMENT);
        return createAccount;
    }
}
