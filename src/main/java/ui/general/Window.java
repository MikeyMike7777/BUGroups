package ui.general;

import database.utils.BUGUtils;
import ui.classmates.ClassmatesPage;
import ui.messages.MessagePage;
import ui.profile.ProfilePage;
import ui.profile.Settings;
import ui.tutors.TutorsPage;
import database.utils.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Default panel of BUGroups, contains tabs and logo, extended by most other windows
public class Window extends JPanel {
    //SpringLayout is used here because it allows us to overlay items on eachother
    private SpringLayout layout;
    public Map<Integer, JPanel> tabMap;
    private JLabel picLabel;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private Dimension tabSize;
    private String currLogoPath = "/BUGroups.png";

    private static int LOGO_WIDTH_OFFSET = -10;

    public static String username;

    Window(Dimension preferredSize, String username) {
        super();
        Window.username = username;
        preferredSize.setSize(preferredSize.getWidth() - 10,
                preferredSize.getHeight() - 30);
        setPreferredSize(preferredSize);
        tabSize = new Dimension((int) (preferredSize.getWidth() - 50),
                (int) (preferredSize.getHeight() - 80));
        //tabbedPane = new JTabbedPane();
        this.tabMap = initTabs();
        initWindow();
        setBackground(Color.LIGHT_GRAY);
    }

    /*
        Our testing constructor will be accepting a map of Integers to JPanels
        where the Integer serves as the panelIndex (position) and the JPanel
        as the Panel for that indexed tab

        JPanel setName() function must be called for the tabs to show their
        proper names
     */
    Window(Dimension preferredSize, Map<Integer, JPanel> tabs) {
        super();
        preferredSize.setSize(preferredSize.getWidth(),
                preferredSize.getHeight() - 20);
        setPreferredSize(preferredSize);
        initWindow();
        setBackground(Color.LIGHT_GRAY);
    }

    /*
     * This whole function can be rewritten/tweaked for what the default
     * tabs should actually be, I just copied and pasted Gabe's testing
     * code since it was mostly functional.
     */
    private Map<Integer, JPanel> initTabs() {
        Map<Integer, JPanel> tabMap = new HashMap<>();
        JPanel testPanel = new HomePage(tabbedPane,getPreferredSize()); //Create an instance of your JPanel extended class
        testPanel.setName("Home Page"); //Set its name to be seen on tab
        tabMap.put(0, testPanel);        //Put it in the map at the next available index
        JPanel testPanel1 = new MessagePage(tabSize);
        testPanel1.setName("Message Boards"); //Set its name to be seen on tab
        tabMap.put(1, testPanel1);        //Put it in the map at the next available index
        JPanel testPanel2 = new ClassmatesPage(); //Create an instance of your JPanel extended class
        testPanel2.setName("Classmates"); //Set its name to be seen on tab
        tabMap.put(2, testPanel2);        //Put it in the map at the next available index
        JPanel testPanel3 = new TutorsPage(); //Create an instance of your JPanel extended class
        testPanel3.setName("Tutors"); //Set its name to be seen on tab
        tabMap.put(3, testPanel3);        //Put it in the map at the next available index
        JPanel testPanel4 = new ProfilePage(tabSize); //Create an instance of your JPanel extended class
        testPanel4.setName("Profile"); //Set its name to be seen on tab
        tabMap.put(4, testPanel4);        //Put it in the map at the next available index
        JPanel testPanel5 = new Settings(tabSize); //Create an instance of your JPanel extended class
        testPanel5.setName("Settings"); //Set its name to be seen on tab
        tabMap.put(5, testPanel5);        //Put it in the map at the next available index
        return tabMap;
    }

    /*
        Seperate the intialization of the window from anything that might need to occur in
        the construction of the ui.general.Window
     */
    public void initWindow(){
        layout = new SpringLayout();
        try{
            initLogo();
            initNavigationBar();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buildLayout();
        setLayout(layout);
        setVisible(true);
    }

    public void initLogo() throws IOException {
        BufferedImage myPicture = ImageIO.read(getClass().getResource("/BUGroups.png"));
        picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(100, 25, Image.SCALE_FAST)));

        //Init our logo listeners
        initLogoListeners();

        //Add the component to the JPanel
        add(picLabel);

    }

    void initLogoListeners(){
        picLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println("FLIP");
                try{
                    flipLogo();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    public void initNavigationBar() throws IOException {
        //tabbedPane = new JTabbedPane();
        tabbedPane.removeAll();
        //remove(tabbedPane);
        this.tabMap = initTabs();
        tabbedPane.setPreferredSize(getPreferredSize());

        BufferedImage userIcon = ImageIO.read(getClass().getResource("/userIcon.png"));
        BufferedImage homeIcon = ImageIO.read(getClass().getResource("/homeIcon.png"));
        BufferedImage messageIcon = ImageIO.read(getClass().getResource("/messageIcon.png"));
        BufferedImage tutorIcon = ImageIO.read(getClass().getResource("/tutorIcon.png"));
        BufferedImage settingsIcon = ImageIO.read(getClass().getResource("/settingsIcon.png"));

        BufferedImage icons[] = {homeIcon, messageIcon, userIcon, tutorIcon, userIcon, settingsIcon};
        int count = 0;
        //Build our navigation bar
        for (Map.Entry<Integer, JPanel> entry : tabMap.entrySet()) {
            tabbedPane.addTab(entry.getValue().getName(), new ImageIcon(icons[count]), entry.getValue(),
                    "Does nothing");

            count++;
            //Sets key for auto navigation to a specified tab, uses the index of 0-?
            tabbedPane.setMnemonicAt(entry.getKey(), KeyEvent.VK_1 + entry.getKey());
        }

        //Add the component to the JPanel
        add(tabbedPane);
    }

    void flipLogo() throws IOException {
        if(currLogoPath.equals("/BUGroups.png")){
            currLogoPath = "/BUGroups2.png";
        } else {
            currLogoPath = "/BUGroups.png";
        }

        BufferedImage myPicture = ImageIO.read(getClass().getResource(currLogoPath));
        picLabel.setIcon(new ImageIcon(myPicture.getScaledInstance(100, 25, Image.SCALE_FAST)));

        revalidate();
        repaint();
    }

    void buildLayout(){
        layout.putConstraint(SpringLayout.EAST, picLabel, LOGO_WIDTH_OFFSET, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, picLabel, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, tabbedPane, 0, SpringLayout.WEST, this);
    }

}
