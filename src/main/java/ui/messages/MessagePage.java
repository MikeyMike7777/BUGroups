package ui.messages;

import javax.swing.*;
import java.awt.*;

public class MessagePage extends JPanel {
    JLabel mainHeader;
    MessageTab dropdown;

    public MessagePage(Dimension d) {
        super();
        setMinimumSize(d);
        createAndDisplay();
    }

    void createAndDisplay() {
        setAlignmentX(CENTER_ALIGNMENT);
        addComponents();
        setVisible(true);
    }

    void addComponents() {
        // header label
        mainHeader = new JLabel("Message Board:");
        add(mainHeader);

        dropdown = new MessageTab(getMinimumSize());
        add(dropdown);
    }

    public void setCurrentMessageBoard(String boardName){
        String[] itemNames = dropdown.getMessageBoardMenuItemNames();
        int index = -1;

        for(int i = 0; i < itemNames.length; i++)
            if(itemNames[i].equals(boardName)){
                index = i;
                break;
            }

        if(index == -1)
            return;

        dropdown.getMessageBoardMenu().getItem(index).doClick();
    }
}
