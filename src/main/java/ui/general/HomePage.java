package ui.general;

import ui.classmates.ClassmatesPage;
import ui.messages.MessagePage;
import ui.profile.ProfileClassList;
import ui.profile.ProfileTutorList;
import ui.tutors.TutorsPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class HomePage extends JPanel {
    JLabel mainHeader;
    JPanel previewContainer;
    JPanel viewMessageBoardsPreview;
    JPanel viewClassmatesPreview;
    JPanel viewTutorsPreview;
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
    }

    private void initPreviewContainers(){

        previewContainer = new JPanel();
        previewContainer.setSize(getPreferredSize());
        previewContainer.setLayout(new BoxLayout(previewContainer, BoxLayout.Y_AXIS));


        buildMessageBoardsPreview();
        previewContainer.add(viewMessageBoardsPreview);
        previewContainer.add(Box.createRigidArea(new Dimension(getPreferredSize().width- 20, 40)));
        buildClassmatesPreview();
        previewContainer.add(viewClassmatesPreview);
        previewContainer.add(Box.createRigidArea(new Dimension(getPreferredSize().width- 20, 40)));
        buildTutorsPreview();
        previewContainer.add(viewTutorsPreview);

        add(previewContainer, BorderLayout.CENTER);
    }

    void buildMessageBoardsPreview(){
        final int BUFFER = 20;
        final int COMPONENT_HEIGHT = getPreferredSize().height/4;

        viewMessageBoardsPreview = new JPanel();
        viewMessageBoardsPreview.setPreferredSize(new Dimension(getPreferredSize().width - BUFFER, COMPONENT_HEIGHT - BUFFER * 3));
        viewMessageBoardsPreview.setBackground(Color.LIGHT_GRAY);
        viewMessageBoardsPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));



        JLabel titleLabel = new JLabel("Message Boards");
        titleLabel.setPreferredSize(new Dimension(viewMessageBoardsPreview.getPreferredSize().width, COMPONENT_HEIGHT/8));

        JLabel subtitleLabel = new JLabel("The message boards are where you can discuss topics with your classmates! \n" +
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
                MessagePage mb = (MessagePage) (window.getComponentAt(1));
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

        viewClassmatesPreview = new JPanel();
        viewClassmatesPreview.setPreferredSize(new Dimension(getPreferredSize().width - BUFFER, COMPONENT_HEIGHT + 60 - BUFFER * 3));
        viewClassmatesPreview.setBackground(Color.LIGHT_GRAY);
        viewClassmatesPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));



        JLabel titleLabel = new JLabel("Classmates");
        titleLabel.setPreferredSize(new Dimension(viewClassmatesPreview.getPreferredSize().width, COMPONENT_HEIGHT/8));

        JLabel subtitleLabel = new JLabel("Classmates is where you can see who's in your class and their profiles! \n" +
                "Your classes are below!");
        subtitleLabel.setPreferredSize(new Dimension(viewClassmatesPreview.getPreferredSize().width, COMPONENT_HEIGHT/8));
        subtitleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        viewClassmatesPreview.add(titleLabel);
        viewClassmatesPreview.add(subtitleLabel);

        ProfileClassList classList = new ProfileClassList();
        Vector<String> classNames = classList.getNames();

        for(String className : classNames){
            JButton classButton = new JButton(className);
            classButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    window.setSelectedIndex(2);
                    ClassmatesPage cm = (ClassmatesPage) window.getComponentAt(2);
                    cm.selectClass(className);
                }
            });
            viewClassmatesPreview.add(classButton);
        }

    }

    void buildTutorsPreview(){
        final int BUFFER = 20;
        final int COMPONENT_HEIGHT = getPreferredSize().height/4;

        viewTutorsPreview = new JPanel();
        viewTutorsPreview.setPreferredSize(new Dimension(getPreferredSize().width - BUFFER, COMPONENT_HEIGHT + 60 - BUFFER * 3));
        viewTutorsPreview.setBackground(Color.LIGHT_GRAY);
        viewTutorsPreview.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));



        JLabel titleLabel = new JLabel("Tutors");
        titleLabel.setPreferredSize(new Dimension(viewTutorsPreview.getPreferredSize().width, COMPONENT_HEIGHT/8));

        JLabel subtitleLabel = new JLabel("Tutors is where you can find available tutors for classes! \n" +
                "Find tutors for each of your classes below!");
        subtitleLabel.setPreferredSize(new Dimension(viewTutorsPreview.getPreferredSize().width, COMPONENT_HEIGHT/8));
        subtitleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        viewTutorsPreview.add(titleLabel);
        viewTutorsPreview.add(subtitleLabel);

        ProfileClassList list = new ProfileClassList();
        Vector<String> classNames = list.getNames();

        for(String className : classNames){
            JButton classButton = new JButton(className);
            classButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    window.setSelectedIndex(3);
                    TutorsPage t = (TutorsPage) window.getComponentAt(3);
                    t.selectClass(className);
                }
            });
            viewTutorsPreview.add(classButton);
        }

    }
}
