import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

// Default panel of BUGroups, contains tabs and logo, extended by most other windows
public class Window extends JPanel {
    //SpringLayout is used here because it allows us to overlay items on eachother
    private SpringLayout layout;
    private Map<Integer, JPanel> tabMap;
    private JLabel picLabel;
    private JTabbedPane tabbedPane;
    private String currLogoPath = "src/main/resources/BUGroups.png";

    /*
        Our default constructor will be accepting a map of Integers to JPanels
        where the Integer serves as the panelIndex (position) and the JPanel
        as the Panel for that indexed tab

        JPanel setName() function must be called for the tabs to show their
        proper names
     */
    Window(Dimension preferredSize, Map<Integer, JPanel> tabMap) {
        super();

        this.tabMap = tabMap;
        initWindow(preferredSize);
        setBackground(Color.LIGHT_GRAY);
        // add default tab and logo stuff here
        // testing-- carsyn
    }

    /*
        Seperate the intialization of the window from anything that might need to occur in
        the construction of the Window
     */
    public void initWindow(Dimension preferredSize){
        setPreferredSize(preferredSize);
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
                System.out.println("FLIP");
                try{
                    flipLogo();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void initNavigationBar(){
        tabbedPane = new JTabbedPane();
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
        layout.putConstraint(SpringLayout.EAST, picLabel, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, picLabel, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, tabbedPane, 0, SpringLayout.WEST, this);
    }

}
