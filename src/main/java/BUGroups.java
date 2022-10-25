import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class BUGroups {
    // testing git setup
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("BU Groups");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(600, 400));
        panel.add(new Login());
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        //frame.add(panel);

        /*
            To see your JPanel added to the navigationBar follow the template below
         */
        Map<Integer, JPanel> tabMap = new HashMap<>();
        JPanel testPanel = new HomePage(frame.getPreferredSize()); //Create an instance of your JPanel extended class
        testPanel.setName("HomePage"); //Set it's name to be seen on tab
        tabMap.put(0, testPanel);        //Put it in the map at the next available index
        JPanel testPanel1 = new JPanel(); //Create an instance of your JPanel extended class
        testPanel1.setName("Test Panel1"); //Set it's name to be seen on tab
        tabMap.put(1, testPanel1);        //Put it in the map at the next available index
        JPanel testPanel2 = new JPanel(); //Create an instance of your JPanel extended class
        testPanel2.setName("Test Panel2"); //Set it's name to be seen on tab
        tabMap.put(2, testPanel2);        //Put it in the map at the next available index
        JPanel testPanel3 = new JPanel(); //Create an instance of your JPanel extended class
        testPanel3.setName("Test Panel3"); //Set it's name to be seen on tab
        tabMap.put(3, testPanel3);        //Put it in the map at the next available index

        frame.add(new Window(frame.getPreferredSize(), tabMap));


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}