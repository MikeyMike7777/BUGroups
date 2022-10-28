package ui.general;

import ui.messages.MessageBoard;
import ui.messages.MessagePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JPanel {
    BoxLayout boxLayout;
    JLabel mainHeader;
    JPanel previewContainer;
    JPanel viewMessageBoardsPreview;
    JPanel viewClassmatesPreview;
    JPanel viewTutorsPreview;
    JPanel viewProfilesPreview;
    JTabbedPane window;

    HomePage(JTabbedPane window, Dimension preferredSize){
        //Call JPanels default constructor
        super();
        this.window = window;

        //Initialize all components and their default values
        initPage(preferredSize);
    }

    private void initPage(Dimension preferredSize){
        //mainHeader Initialization
        setPreferredSize(preferredSize);
        mainHeader = new JLabel("HOME");
        //mainHeader.setSize(new Dimension(100, 100));
        add(mainHeader, BorderLayout.PAGE_START);

        //Initialize the preview containers
        initPreviewContainers();

        buildLayout();
    }

    private void initPreviewContainers(){
        //messageBoardsPreview Initialization

        //viewClassmatesPreview Initialization
//        viewClassmatesPreview = new JPanel();
//        viewClassmatesPreview.add(new JLabel("View Classmates"));
//
//        //viewTutorsPreview Initialization
//        viewTutorsPreview = new JPanel();
//        viewTutorsPreview.add(new JLabel("View Tutors"));
//
//        //viewProfilesPreview Initialization
//        viewProfilesPreview = new JPanel();
//        viewProfilesPreview.add(new JLabel("View Profiles"));

        //previewContainer Initialization
        previewContainer = new JPanel();
        previewContainer.setSize(getPreferredSize());
        previewContainer.setLayout(new BoxLayout(previewContainer, BoxLayout.Y_AXIS));
        //previewContainer.setLayout(new GridLayout());

        buildMessageBoardsPreview();
        previewContainer.add(viewMessageBoardsPreview);
//        previewContainer.add(viewClassmatesPreview);
//        previewContainer.add(viewTutorsPreview);
//        previewContainer.add(viewProfilesPreview);

        add(previewContainer, BorderLayout.CENTER);
    }

    void buildLayout(){
//        boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
//        setLayout(boxLayout);
    }

    void buildMessageBoardsPreview(){
        final int BUFFER = 20;
        final int COMPONENT_HEIGHT = getPreferredSize().height/4;

        viewMessageBoardsPreview = new JPanel();
        viewMessageBoardsPreview.setPreferredSize(new Dimension(getPreferredSize().width - BUFFER, COMPONENT_HEIGHT - BUFFER * 3));
        System.out.println(viewMessageBoardsPreview.getSize());
        viewMessageBoardsPreview.setBackground(Color.LIGHT_GRAY);
        viewMessageBoardsPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));



        JLabel titleLabel = new JLabel("Message Boards");
        titleLabel.setPreferredSize(new Dimension(viewMessageBoardsPreview.getPreferredSize().width, COMPONENT_HEIGHT/8));

        JLabel subtitleLabel = new JLabel("The message boards is where you can discuss topics with your classmates! \n" +
                "Most popular boards are below!");
        subtitleLabel.setPreferredSize(new Dimension(viewMessageBoardsPreview.getPreferredSize().width, COMPONENT_HEIGHT/8));
        subtitleLabel.setHorizontalAlignment(SwingConstants.LEFT);



        JButton biologyQuickSeek = new JButton("Biology and Health Sciences");
        biologyQuickSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setSelectedIndex(1);
                MessagePage mb = (MessagePage) window.getComponentAt(1);
                mb.setCurrentMessageBoard("Biology and Health Sciences");
            }
        });

        JButton mathQuickSeek = new JButton("Math and Physics");
        mathQuickSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setSelectedIndex(1);
                MessagePage mb = (MessagePage) window.getComponentAt(1);
                mb.setCurrentMessageBoard("Math and Physics");
            }
        });

        JButton engineeringQuickSeek = new JButton("Engineering and Comp Sci");
        engineeringQuickSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setSelectedIndex(1);
                MessagePage mb = (MessagePage) window.getComponentAt(1);
                mb.setCurrentMessageBoard("Engineering and Computer Science");
            }
        });

        JButton chemistryQuickSeek = new JButton("Chem and Biochem");
        chemistryQuickSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setSelectedIndex(1);
                MessagePage mb = (MessagePage) window.getComponentAt(1);
                mb.setCurrentMessageBoard("Chemistry and Biochemistry");
            }
        });



        viewMessageBoardsPreview.add(titleLabel);
        viewMessageBoardsPreview.add(subtitleLabel);
        viewMessageBoardsPreview.add(mathQuickSeek);
        viewMessageBoardsPreview.add(biologyQuickSeek);
        viewMessageBoardsPreview.add(chemistryQuickSeek);
        viewMessageBoardsPreview.add(engineeringQuickSeek);


    }

    void buildClassmatesPreview(){
        final int BUFFER = 20;
        final int COMPONENT_HEIGHT = getPreferredSize().height/4;

        viewMessageBoardsPreview = new JPanel();
        viewMessageBoardsPreview.setPreferredSize(new Dimension(getPreferredSize().width - BUFFER, COMPONENT_HEIGHT - BUFFER * 3));
        System.out.println(viewMessageBoardsPreview.getSize());
        viewMessageBoardsPreview.setBackground(Color.LIGHT_GRAY);
        viewMessageBoardsPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));



        JLabel titleLabel = new JLabel("Classmates");
        titleLabel.setPreferredSize(new Dimension(viewMessageBoardsPreview.getPreferredSize().width, COMPONENT_HEIGHT/8));

        JLabel subtitleLabel = new JLabel("The message boards is where you can discuss topics with your classmates! \n" +
                "Most popular boards are below!");
        subtitleLabel.setPreferredSize(new Dimension(viewMessageBoardsPreview.getPreferredSize().width, COMPONENT_HEIGHT/8));
        subtitleLabel.setHorizontalAlignment(SwingConstants.LEFT);



        JButton biologyQuickSeek = new JButton("Biology and Health Sciences");
        biologyQuickSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setSelectedIndex(1);
                MessagePage mb = (MessagePage) window.getComponentAt(1);
                mb.setCurrentMessageBoard("Biology and Health Sciences");
            }
        });

        JButton mathQuickSeek = new JButton("Math and Physics");
        biologyQuickSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setSelectedIndex(1);
                MessagePage mb = (MessagePage) window.getComponentAt(1);
                mb.setCurrentMessageBoard("Math and Physics");
            }
        });

        JButton engineeringQuickSeek = new JButton("Engineering and Comp Sci");
        biologyQuickSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setSelectedIndex(1);
                MessagePage mb = (MessagePage) window.getComponentAt(1);
                mb.setCurrentMessageBoard("Engineering and Computer Science");
            }
        });

        JButton chemistryQuickSeek = new JButton("Chem and Biochem");
        biologyQuickSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setSelectedIndex(1);
                MessagePage mb = (MessagePage) window.getComponentAt(1);
                mb.setCurrentMessageBoard("Chemistry and Biochemistry");
            }
        });



        viewMessageBoardsPreview.add(titleLabel);
        viewMessageBoardsPreview.add(subtitleLabel);
        viewMessageBoardsPreview.add(mathQuickSeek);
        viewMessageBoardsPreview.add(biologyQuickSeek);
        viewMessageBoardsPreview.add(chemistryQuickSeek);
        viewMessageBoardsPreview.add(engineeringQuickSeek);


    }
}
