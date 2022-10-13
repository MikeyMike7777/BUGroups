import javax.swing.*;

class BUGroups {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("BU Groups");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Window bugWindow = new Window();
        bugWindow.setOpaque(true);
        frame.setContentPane(bugWindow);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
