import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ClassmatesTab extends JPanel{

    ClassList list = new ClassList(); // need to connect this to what called it
    String [] classNames = new String[list.getNames().length + 1];
    HashMap<String, Integer> boardKeys = new HashMap<>();
    HashMap<String, JPanel> classes = new HashMap<>();
    JPanel currClass = new JPanel();

    ClassmatesTab() {
        super();
        loadClasses();
        createAndDisplay();
    }

    void loadClasses(){
        classNames[0] = "Select A Class";
        for (int i = 1; i < classNames.length; ++i) {
            classNames[i] = list.getNames()[i - 1];
            boardKeys.put(classNames[i], i);
            classes.put(classNames[i], new JPanel());
            classes.get(classNames[i]).add(new JLabel(classNames[i]));
        }
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
        select.setText("Select Class");
        select.setAlignmentX(CENTER_ALIGNMENT);
        select.setPreferredSize(new Dimension(300, 30));
        JMenuItem[] boards = createBoardOptions();
        for (int i = 0; i < classNames.length; ++i) {
            select.add(boards[i]);
        }
        bar.add(select);
        return bar;
    }

    JMenuItem[] createBoardOptions() {
        JMenuItem[] items = new JMenuItem[classNames.length];
        for (int i = 0; i < classNames.length; ++i) {
            items[i] = new JMenuItem(classNames[i]);
            if (i > 0)
                items[i].addActionListener(new ClassmatesTab.MenuActionListener());
        }
        return items;
    }

    class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = ((JMenuItem)e.getSource()).getText();
            ((JMenu)((JPopupMenu)((JMenuItem)e.getSource()).getParent()).
                    getInvoker()).setText(s);
            currClass = classes.get(s);

            // adds panels-- theoretically i want it to replace them
            // (hide old one, show new one) but idk how to do that
            add(currClass);
        }
    }

    /*
    when user clicks "update classes" in profile, it should construct a new
    Classmates Tab. ClassList (in profile) should start with nothing in it
     */
}


