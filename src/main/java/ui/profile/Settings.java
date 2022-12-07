package ui.profile;

import database.student.Student;
import database.utils.BUGUtils;
import database.utils.Controller;
import ui.classmates.ClassmatesTab;
import ui.general.Login;
import ui.general.Window;
import ui.profile.ProfilePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Settings extends JPanel {
    ClassmatesTab dropdown;
    public Settings(Dimension preferredSize) {
        super();
        initializeWindow(preferredSize);
    }

    private void initializeWindow(Dimension preferredSize) {
        setMinimumSize(preferredSize);
        addComponents();
    }

    private void addComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addLabel());
        panel.add(addChangeName());
        panel.add(addChangePassword());
        panel.add(addChangePhoneNumber());
        panel.add(addDeleteAccount());
        panel.add(addReportBug());
        panel.add(addLogOut());
        panel.setAlignmentX(CENTER_ALIGNMENT);
        add(panel);
    }

    private Component addLabel() {
        JLabel label = new JLabel("Settings");
        label.setAlignmentX(CENTER_ALIGNMENT);
        return label;
    }
    private Component addLogOut(){
        JButton logOut = new JButton("Log Out");
        logOut.setAlignmentX(CENTER_ALIGNMENT);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane
                        .showConfirmDialog(null,
                                "Are you sure you want to log out?",
                                "Warning", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    Container temp = (Container)getRootPane().getContentPane();
                    JPanel root = new JPanel();
                    temp.removeAll();
                    root.add(new Login(new Dimension(BUGUtils.APP_WIDTH, BUGUtils.APP_HEIGHT)));
                    temp.add(root);
                    temp.validate();
                    temp.repaint();
                }
            }
        });
        return logOut;
    }
    private Component addReportBug(){
        JButton report = new JButton("Report Bug");
        JLabel label = new JLabel("What issue were you having?");
        report.setAlignmentX(CENTER_ALIGNMENT);
        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel labelPanel = new JPanel();
                JPanel buttonPanel = new JPanel();
                JDialog dialog = new JDialog();
                JPanel changePanel = new JPanel();
                JButton done = new JButton("Done");
                JButton back = new JButton("Cancel");
                JTextPane bug = new JTextPane();
                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));

                done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(bug.getText().length() < 1){
                            JOptionPane.showMessageDialog(getRootPane().getParent(),
                                    "Please enter something that's BUGging you",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            dialog.setVisible(true);
                        }
                        else {
                            JOptionPane.showMessageDialog(null,
                                    "Your BUG has been reported!",
                                    "BUG Report", JOptionPane.PLAIN_MESSAGE);
                            BUGUtils.controller.reportBug(bug.getText());
                            dialog.dispose();
                        }
                    }
                });
                back.addActionListener(new BackActionListener(dialog));

                labelPanel.add(label);
                buttonPanel.add(done);
                buttonPanel.add(back);

                changePanel.add(labelPanel);
                changePanel.add(bug);
                changePanel.add(buttonPanel);

                dialog.add(changePanel);
                dialog.setSize(new Dimension(300, 175));
                dialog.setVisible(true);

                changePanel.setAlignmentX(CENTER_ALIGNMENT);
                label.setAlignmentX(CENTER_ALIGNMENT);
                bug.setAlignmentX(CENTER_ALIGNMENT);
                done.setAlignmentX(CENTER_ALIGNMENT);
                back.setAlignmentX(CENTER_ALIGNMENT);
                buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
                labelPanel.setAlignmentX(CENTER_ALIGNMENT);
            }
        });
        return report;
    }
    private Component addDeleteAccount(){
        JButton deleteAccount = new JButton("Delete Account");
        deleteAccount.setAlignmentX(CENTER_ALIGNMENT);
        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane
                        .showConfirmDialog(null,
                                "Are you sure you want to delete your account?",
                                "Warning", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    BUGUtils.controller.deleteAccountByID(Window.username);
                    Container temp = (Container)getRootPane().getContentPane();
                    JPanel root = new JPanel();
                    temp.removeAll();
                    root.add(new Login(new Dimension(BUGUtils.APP_WIDTH, BUGUtils.APP_HEIGHT)));
                    temp.add(root);
                    temp.validate();
                    temp.repaint();
                }
            }
        });
        return deleteAccount;
    }
    private Component addChangeName() {
        JButton phoneNumber = new JButton("Change Name");
        JLabel label = new JLabel("First Name");
        JLabel label1 = new JLabel("Last Name");
        phoneNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel changePanel = new JPanel();
                JPanel text = new JPanel();
                JPanel text1 = new JPanel();
                JPanel buttonPanel = new JPanel();
                JButton done = new JButton("Done");
                JButton back = new JButton("Cancel");
                JDialog changeDialog = new JDialog();
                JTextField first = new JTextField(15);
                JTextField last = new JTextField(15);

                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));

                changePanel.add(label);
                text.add(first);
                changePanel.add(text);

                changePanel.add(label1);
                text1.add(last);
                changePanel.add(text1);

                buttonPanel.add(done);
                buttonPanel.add(back);
                changePanel.add(buttonPanel);

                buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
                label.setAlignmentX(CENTER_ALIGNMENT);
                label1.setAlignmentX(CENTER_ALIGNMENT);
                done.setAlignmentX(CENTER_ALIGNMENT);
                changePanel.setAlignmentX(CENTER_ALIGNMENT);
                done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = first.getText() + " " + last.getText();
                        if(first.getText().length() < 1){
                            JOptionPane.showMessageDialog(getRootPane().getParent(),
                                    "Please enter a valid first name",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            changeDialog.setVisible(true);
                        }
                        else if(last.getText().length() < 1){
                            JOptionPane.showMessageDialog(getRootPane().getParent(),
                                    "Please enter a valid last name",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            changeDialog.setVisible(true);
                        }
                        else {
                            BUGUtils.controller.updateProfileName(Window.username, name);
                            changeDialog.dispose();
                            ((ProfilePage) ((Window) ((JPanel) getRootPane().getContentPane()
                                    .getComponent(0)).getComponent(0)).tabMap.get(4))
                                    .repaintUserInfo();
                        }
                    }
                });
                back.addActionListener(new BackActionListener(changeDialog));

                changeDialog.add(changePanel);
                changeDialog.setSize(new Dimension(300, 175));
                changeDialog.setVisible(true);
            }
        });
        phoneNumber.setAlignmentX(CENTER_ALIGNMENT);
        return phoneNumber;
    }
    private Component addChangePassword() {
        JButton password = new JButton("Change Password");
        JLabel label = new JLabel("Change Password");
        JLabel label1 = new JLabel("Confirm Password");
        password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel changePanel = new JPanel();
                JPanel text = new JPanel();
                JPanel text1 = new JPanel();
                JPanel buttonPanel = new JPanel();
                JButton done = new JButton("Done");
                JButton back = new JButton("Cancel");
                JDialog changeDialog = new JDialog();
                JPasswordField password = new JPasswordField(15);

                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
                changePanel.add(label);
                text.add(password);
                changePanel.add(text);
                changePanel.add(label1);
                text1.add(new JPasswordField(15));
                changePanel.add(text1);
                buttonPanel.add(done);
                buttonPanel.add(back);
                changePanel.add(buttonPanel);

                buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
                label.setAlignmentX(CENTER_ALIGNMENT);
                label1.setAlignmentX(CENTER_ALIGNMENT);
                done.setAlignmentX(CENTER_ALIGNMENT);
                changePanel.setAlignmentX(CENTER_ALIGNMENT);
                done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(password.getText().length() < 1) {
                            JOptionPane.showMessageDialog(getRootPane().getParent(),
                                    "Please enter a valid password",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            changeDialog.setVisible(true);
                        }
                        else {
                            if (((JPasswordField) text.getComponent(0)).getText()
                                    .equals(((JPasswordField) text1.getComponent(0)).getText())) {
                                BUGUtils.controller.changePassword(Window.username,
                                        ((JPasswordField) text.getComponent(0)).getText());
                                changeDialog.dispose();
                            } else {
                                JOptionPane.showMessageDialog(getRootPane().getParent(),
                                        "Passwords don't match!", "Error",
                                        JOptionPane.WARNING_MESSAGE);
                                changeDialog.setVisible(true);
                            }


                        }
                    }
                });
                back.addActionListener(new BackActionListener(changeDialog));

                changeDialog.add(changePanel);
                changeDialog.setSize(new Dimension(300, 175));
                changeDialog.setVisible(true);
            }
        });
        password.setAlignmentX(CENTER_ALIGNMENT);
        return password;
    }

    private Component addChangePhoneNumber() {
        JButton phoneNumber = new JButton("Change Phone Number");
        JLabel label = new JLabel("Change Phone Number");
        phoneNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel changePanel = new JPanel();
                JPanel buttonPanel = new JPanel();
                JPanel text = new JPanel();
                JButton done = new JButton("Done");
                JButton back = new JButton("Cancel");
                JDialog changeDialog = new JDialog();
                JTextField phone = new JTextField(15);

                changePanel.setLayout(new BoxLayout(changePanel, BoxLayout.Y_AXIS));
                changePanel.add(label);
                text.add(phone);
                changePanel.add(text);
                buttonPanel.add(done);
                buttonPanel.add(back);
                changePanel.add(buttonPanel);

                buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
                label.setAlignmentX(CENTER_ALIGNMENT);
                done.setAlignmentX(CENTER_ALIGNMENT);
                changePanel.setAlignmentX(CENTER_ALIGNMENT);
                done.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(phone.getText().length() < 10) {
                            JOptionPane.showMessageDialog(getRootPane().getParent(),
                                    "Please enter a valid phone number",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            changeDialog.setVisible(true);
                        }
                        else {
                            try {
                                Long.parseLong(phone.getText());
                                //continue if is numerical
                                BUGUtils.controller.updateProfilePhoneNumber(Window.username, phone.getText());
                                changeDialog.dispose();
                                ((ProfilePage) ((Window) ((JPanel) getRootPane().getContentPane()
                                        .getComponent(0)).getComponent(0)).tabMap.get(4))
                                        .repaintUserInfo();
                            } catch (NumberFormatException nan) {
                                JOptionPane.showMessageDialog(getRootPane().getParent(),
                                        "Please only use numbers",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                changeDialog.setVisible(true);
                            }
                        }
                    }
                });
                back.addActionListener(new BackActionListener(changeDialog));

                changeDialog.add(changePanel);
                changeDialog.setSize(new Dimension(300, 110));
                changeDialog.setVisible(true);
            }
        });
        phoneNumber.setAlignmentX(CENTER_ALIGNMENT);
        return phoneNumber;
    }

    private static class BackActionListener implements ActionListener {
        private final JDialog changeDialog;

        public BackActionListener(JDialog changeDialog) {
            this.changeDialog = changeDialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            changeDialog.dispose();
        }
    }
}
