package ui.classmates;


import database.utils.BUGUtils;

import javax.swing.*;
import java.awt.*;

public class ClassmatesPage extends JPanel {
    ClassmatesTab dropdown;

    public ClassmatesPage() {
        super();
        createAndDisplay();

        // create dummy data for testing purposes FIXME: come back to this when you're done
        //BUGUtils.controller.generate();
    }

    void createAndDisplay() {
        setMinimumSize(new Dimension(600, 350));
        setMaximumSize(new Dimension(600, 600));
        setAlignmentX(CENTER_ALIGNMENT);
        addComponents();
        setVisible(true);
    }

    void addComponents() {
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
