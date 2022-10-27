import ui.general.*;

import javax.swing.*;
import java.awt.*;

class BUGroups {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("BU Groups");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 400));
        panel.add(new Login(panel.getPreferredSize()));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        frame.add(panel); // comment out when testing other pages

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