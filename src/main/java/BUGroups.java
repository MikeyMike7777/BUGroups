import ui.general.*;

import javax.swing.*;
import java.awt.*;



class BUGroups {
    static final int APP_WIDTH = 800;
    static final int APP_HEIGHT = 600;

    private static void createAndShowGUI() {
        JFrame application = new JFrame("BU Groups");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        application.setResizable(false);

        JPanel panel = new JPanel();
        application.add(panel); // comment out when testing other pages
        panel.setPreferredSize(application.getPreferredSize());
        panel.add(new Login(panel.getPreferredSize()));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);


        application.pack();
        application.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}