import ui.general.*;

import javax.swing.*;
import java.awt.*;

class BUGroups {
    final static int APP_WIDTH = 800;
    final static int APP_HEIGHT = 600;

    private static void createAndShowGUI() {
        JFrame app = new JFrame("BU Groups");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        app.setResizable(false);

        JPanel panel = new JPanel();
        panel.setPreferredSize(app.getPreferredSize());
        panel.add(new Login(panel.getPreferredSize()));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        app.add(panel); // comment out when testing other pages

        app.pack();
        app.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}