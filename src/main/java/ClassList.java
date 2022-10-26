import javax.swing.*;
import java.awt.*;

public class ClassList extends JPanel {

    String[] classes = {
            "CSI 3336 - Systems Programming",
            "CSI 3471 - Software Engineering I",
            "WGS 2300 - Women and Gender Studies",
            "GEO 1306 - The Dynamic Earth"
    };

    JLabel header;

    JList<String> classList;

    ClassList(){
        super();
        GridLayout grid = new GridLayout(2, 1);
        grid.setVgap(0);
        setLayout(grid);
        createAndDisplay();

    }

    void createAndDisplay() {
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        // header label
        header = new JLabel("Current Classes:");
        add(header);


        classList = new JList<>(classes);
        add(classList);

        // scroll bar
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(500, 100);
    }

    String[] getNames(){
        return classes;
    }

}
