package ui.general;

import ui.classmates.ClassmatesTab;

import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {
    ClassmatesTab dropdown;

    public Settings(Dimension preferredSize) {
        super();
        initializeWindow(preferredSize);
    }

    void initializeWindow(Dimension preferredSize) {
        setMinimumSize(preferredSize);
        addComponents();
    }

    void addComponents() {

    }

}
