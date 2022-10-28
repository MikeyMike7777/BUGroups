package ui.tutors;

import javax.swing.*;
import java.awt.*;

public class TutorsPage extends JPanel{
    TutorsTab dropdown;

    public TutorsPage() {
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
        // scroll bar
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(500, 100);

        dropdown = new TutorsTab();
        add(dropdown);
    }

    public void selectClass(String className){
        JMenu menu = dropdown.getClassMenu();
        String[] classNames = dropdown.getClassNames();

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
