import javax.swing.*;
import java.awt.*;

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
        frame.add(panel);

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