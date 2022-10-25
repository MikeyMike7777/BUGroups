import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/* not sure if this works (i don't remember how we're doing things with the
Window class), really just copied and pasted Bryce's work but theoretically
it should do similar things */
public class TutorsTab extends JPanel{
    static final String[] names = {"Select Message Board", "Biology and " +
            "Health Sciences", "Business", "Chemistry and Biochemistry", "Education " +
            "and Social Work", "Engineering and Computer Science", "English and " +
            "Journalism", "Fine Arts", "Foreign Languages", "History and Political " +
            "Science", "Math and Physics", "Philosophy and BIC", "Psychology and " +
            "Sociology"};

    HashMap<String, Integer> boardKeys = new HashMap<>();

    TutorsTab() {
        super();
        for (int i = 1; i < 13; ++i)
            boardKeys.put(names[i], i);
        createAndDisplay();
    }

    void createAndDisplay() {
        setSize(new Dimension(600, 400));
        setAlignmentX(LEFT_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(addBoardMenu());
        add(panel);
    }

    Component addBoardMenu() {
        JMenuBar bar = new JMenuBar();
        bar.setPreferredSize(new Dimension(300, 30));
        JMenu select = new JMenu();
        select.setText("Select Message Board");
        select.setAlignmentX(CENTER_ALIGNMENT);
        select.setPreferredSize(new Dimension(300, 30));
        JMenuItem[] boards = createBoardOptions();
        for (int i = 0; i < 13; ++i)
            select.add(boards[i]);
        bar.add(select);
        return bar;
    }

    JMenuItem[] createBoardOptions() {
        JMenuItem[] items = new JMenuItem[13];
        for (int i = 0; i < 13; ++i) {
            items[i] = new JMenuItem(names[i]);
            if (i > 0)
                items[i].addActionListener(new TutorsTab.MenuActionListener());
        }
        return items;
    }

    class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = ((JMenuItem)e.getSource()).getText();
            ((JMenu)((JPopupMenu)((JMenuItem)e.getSource()).getParent()).
                    getInvoker()).setText(s);
            MessageBoard board = new MessageBoard();
            board.iD = boardKeys.get(s);
            board.name = s;
            add(board);
        }
    }
}


