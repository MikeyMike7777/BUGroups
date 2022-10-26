package ui.messages;

import javax.swing.*;
import java.awt.*;

public class MessagePage extends JPanel {
    JLabel mainHeader;

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

        // scroll bar
        ScrollPane scrolls = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrolls.setSize(500, 100);

        JPanel dropdown = new MessageTab(getMinimumSize());
        add(dropdown);
    }
}
