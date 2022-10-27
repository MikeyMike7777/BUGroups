package ui.classmates;

import javax.swing.*;

public class ClassmateInfoDialog extends JDialog{
    Classmate cm;
    Classmate parent;
    ClassmateInfoDialog(Classmate owner){
        super(SwingUtilities.windowForComponent(owner));
        parent = owner;
        cm = new Classmate(parent.getName());
        cm.setFocusable(false);
        //createAndDisplay();
    }
}
