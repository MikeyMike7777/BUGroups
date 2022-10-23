import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class HomePage extends JPanel {
    JLabel mainHeader;
    JPanel previewContainer;
    JPanel messageBoardsPreview;
    JPanel viewClassmatesPreview;
    JPanel viewTutorsPreview;
    JPanel viewProfilesPreview;

    HomePage(){
        //Call JPanels default constructor
        super();

        //Initialize all components and their default values
        initPage();
    }

    private void initPage(){
        //mainHeader Initialization
        mainHeader = new JLabel("HOME");
        mainHeader.setSize(new Dimension(100, 100));
        add(mainHeader);

        //messageBoardsPreview Initialization
        messageBoardsPreview = new JPanel();
        messageBoardsPreview.add(new JLabel("View Message Boards"));

        //viewClassmatesPreview Initialization
        viewClassmatesPreview = new JPanel();
        viewClassmatesPreview.add(new JLabel("View Classmates"));

        //viewTutorsPreview Initialization
        viewTutorsPreview = new JPanel();
        viewTutorsPreview.add(new JLabel("View Tutors"));

        //viewProfilesPreview Initialization
        viewProfilesPreview = new JPanel();
        viewProfilesPreview.add(new JLabel("View Profiles"));

        //previewContainer Initialization
        previewContainer = new JPanel();
        previewContainer.setLayout(new GridLayout());

        previewContainer.add(messageBoardsPreview);
        previewContainer.add(viewClassmatesPreview);
        previewContainer.add(viewTutorsPreview);
        previewContainer.add(viewProfilesPreview);
        add(previewContainer);

    }
}
