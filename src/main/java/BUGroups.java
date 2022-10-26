import ui.general.Login;

import javax.swing.*;
import java.awt.*;

class BUGroups {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("BU Groups");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 400));
        panel.add(new Login(panel.getPreferredSize()));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        frame.add(panel); // comment out when testing other pages

        /*
         * BEGIN TESTING CODE
         */
//        Map<Integer, JPanel> tabMap = new HashMap<>();
//        JPanel testPanel = new ui.general.HomePage(panel.getPreferredSize()); //Create an instance of your JPanel extended class
//        testPanel.setName("ui.general.HomePage"); //Set its name to be seen on tab
//        tabMap.put(0, testPanel);        //Put it in the map at the next available index
//        JPanel testPanel1 = new JPanel(); //Create an instance of your JPanel extended class
//        testPanel1.setName("ui.messages.Message Board"); //Set its name to be seen on tab
//        tabMap.put(1, testPanel1);        //Put it in the map at the next available index
//        JPanel testPanel2 = new JPanel(); //Create an instance of your JPanel extended class
//        testPanel2.setName("Classmates"); //Set its name to be seen on tab
//        tabMap.put(2, testPanel2);        //Put it in the map at the next available index
//        JPanel testPanel3 = new ui.tutors.TutorsPage(); //Create an instance of your JPanel extended class
//        testPanel3.setName("Tutors"); //Set its name to be seen on tab
//        tabMap.put(3, testPanel3);        //Put it in the map at the next available index
//        JPanel testPanel4 = new JPanel(); //Create an instance of your JPanel extended class
//        testPanel4.setName("Profile"); //Set its name to be seen on tab
//        tabMap.put(4, testPanel4);        //Put it in the map at the next available index
        /*
         *  END TESTING CODE
         */

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