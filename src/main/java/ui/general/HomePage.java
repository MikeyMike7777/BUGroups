package ui.general;

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

    HomePage(Dimension preferredSize){
        //Call JPanels default constructor
        super();

        //Initialize all components and their default values
        initPage(preferredSize);
    }

    private void initPage(Dimension preferredSize){
        //mainHeader Initialization
        setPreferredSize(preferredSize);
        mainHeader = new JLabel("HOME");
        //mainHeader.setSize(new Dimension(100, 100));
        add(mainHeader);

        //Initialize the preview containers
        initPreviewContainers();



    }

    private void initPreviewContainers(){
        //messageBoardsPreview Initialization
        messageBoardsPreview = new JPanel();
        messageBoardsPreview.add(new JLabel("View ui.messages.Message Boards"));

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
        previewContainer.setLayout(new GridLayout(4, 1));
        add(previewContainer);
    }
}
