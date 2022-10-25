import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

// Default panel of BUGroups, contains tabs and logo, extended by most other windows
public class Window extends JPanel {
    Window() {
        super();
        initWindow();
        // add default tab and logo stuff here
        // testing-- carsyn
    }

    public void initWindow(){
        setPreferredSize(new Dimension(600, 400));
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        ImageIcon icon = new ImageIcon("src/main/resources/icons8-user-30.png");
        tabbedPane.addTab("Message Board", icon, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Classmates", icon, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Tutors", icon, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);


        tabbedPane.addTab("Profile", icon, panel4,
                "Does nothing at all");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        add(tabbedPane);

    }
}
