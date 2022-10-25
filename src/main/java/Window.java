import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Default panel of BUGroups, contains tabs and logo, extended by most other windows
public class Window extends JPanel {
    Window() {
        super();
        initWindow();
        // add default tab and logo stuff here
        // testing-- carsyn
    }

    public void initWindow(){
        setSize(new Dimension(600, 400));
        setLayout(new GridLayout(1, 1));
//        try{
//            initLogo();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        initNavigationBar();

    }

    public void initLogo() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("src/main/resources/BUGroups.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(200, 100, Image.SCALE_FAST)));
        setSize(200, 100);
        add(picLabel);

    }

    public void initNavigationBar(){
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        ImageIcon icon = new ImageIcon("src/main/resources/icons8-user-30.png");

        tabbedPane.addTab("Message Board", icon, new JLabel("TEST1"),
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Classmates", icon, new JLabel("TEST2"),
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Tutors", icon, new JLabel("TEST3"),
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.addTab("Profile", icon, new JLabel("TEST4"),
                "Does nothing at all");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        add(tabbedPane);
    }
}
