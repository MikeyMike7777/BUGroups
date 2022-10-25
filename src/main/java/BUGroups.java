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
        JPanel testPanel = new JPanel(); //Create an instance of your JPanel extended class
        testPanel.setName("Test Panel"); //Set it's name to be seen on tab
        tabMap.put(0, testPanel);        //Put it in the map at the next available index

        frame.add(new Window(tabMap));


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