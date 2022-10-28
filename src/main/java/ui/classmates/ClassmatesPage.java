package ui.classmates;

import javax.swing.*;
import java.awt.*;

public class ClassmatesPage extends JPanel {
    JLabel mainHeader;
    ClassmatesTab dropdown;

    public ClassmatesPage() {
        super();
        createAndDisplay();
    }

    void createAndDisplay() {
        setMinimumSize(new Dimension(600, 350));
        setAlignmentX(CENTER_ALIGNMENT);
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        // header label
        //mainHeader = new JLabel("Class:");
        //add(mainHeader);

        // scroll bar
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(500, 100);

        /* in the final iteration we'll add the default "no classes" label but
        for iteration 2 we'll just have hardcoded values */

        // add(new JLabel ("No current classes"));
        dropdown = new ClassmatesTab();
        add(dropdown);
    }

    public void selectClass(String className){
        JMenu menu = dropdown.getClassMenu();
        String classNames[] = dropdown.getClassNames();

        int index = -1;

        for(int i = 0; i < classNames.length; i++){
            if(classNames[i].equals(className)){
                index = i;
                break;
            }
        }

        if(index == -1){
            return;
        }

        menu.getItem(index).doClick();
     }
}
