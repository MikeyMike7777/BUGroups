package ui.general;

import ui.classmates.ClassmatesPage;
import ui.general.HomePage;
import ui.messages.MessagePage;
import ui.profile.ProfilePage;
import ui.tutors.TutorsPage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Default panel of BUGroups, contains tabs and logo, extended by most other windows
public class Window extends JPanel {
    //SpringLayout is used here because it allows us to overlay items on eachother
    private SpringLayout layout;
    private Map<Integer, JPanel> tabMap;
    private JLabel picLabel;
    private JTabbedPane tabbedPane;
    private String currLogoPath = "src/main/resources/BUGroups.png";
    private Dimension tabSize;

    private static int LOGO_WIDTH_OFFSET = -10;

    Window(Dimension preferredSize) {
        super();
        preferredSize.setSize(preferredSize.getWidth(),
                preferredSize.getHeight() - 30);
        setPreferredSize(preferredSize);
        tabSize = new Dimension((int) (preferredSize.getWidth() - 50),
                (int) (preferredSize.getHeight() - 80));
        tabbedPane = new JTabbedPane();
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
        this.tabMap = tabs;
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
        testPanel.setName("HomePage"); //Set its name to be seen on tab
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        initNavigationBar();
        buildLayout();
        setLayout(layout);
    }

    public void initLogo() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File(currLogoPath));
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

    public void initNavigationBar(){
        //tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(getPreferredSize());

        ImageIcon icon = new ImageIcon("src/main/resources/userIcon.png");

        //Build our navigation bar
        for (Map.Entry<Integer, JPanel> entry : tabMap.entrySet()) {
            tabbedPane.addTab(entry.getValue().getName(), icon, entry.getValue(),
                    "Does nothing");

            //Sets key for auto navigation to a specified tab, uses the index of 0-?
            tabbedPane.setMnemonicAt(entry.getKey(), KeyEvent.VK_1 + entry.getKey());
        }

        //Init our navigation bar Listeners
        initNavigationBarListeners();
        //Add the component to the JPanel
        add(tabbedPane);
    }

    void initNavigationBarListeners(){

    }

    void flipLogo() throws IOException {
        if(currLogoPath.equals("src/main/resources/BUGroups.png")){
            currLogoPath = "src/main/resources/BUGroups2.png";
        } else {
            currLogoPath = "src/main/resources/BUGroups.png";
        }

        BufferedImage myPicture = ImageIO.read(new File(currLogoPath));
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
