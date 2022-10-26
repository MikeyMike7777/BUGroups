import javax.swing.*;
import java.awt.*;

public class Message extends JPanel {
    static int counter = 0;

    private static final String[] names = {
            "Carsyn Smeda",
            "Bryce Robinson",
            "Mikey Mathews",
            "Gabriel Goulis",
            "Carson Buntin"
    };

    private static final String[] messages = {
            "Hi everyone! I'm looking for a tutor in Databases but don't " +
                    "see any listed. Has anyone taken it who could help me " +
                    "out one or two days a week?",
            "Howdy, does anyone have an opinion on which professor to take " +
                    "for software 1? I've heard Cerny is difficult but you " +
                    "learn more than with the other options.",
            "Is anyone taking SPA 1310 with someone other than Spinks? None " +
                    "of my classmates are on here and I desperately need a " +
                    "study group lol",
            "Yeah I'm in the same boat for 1320. Spinks is difficult but if " +
                    "you study the vocab and practice saying stuff out loud " +
                    "you should do fine.",
            "Yo everyone and their mother is tutoring for geology, how am I " +
                    "supposed to pick a good one? Not trying to fail my easy" +
                    " science credits haha"
    };

    String name;
    String message;
    Boolean isReply = false;

    Message() {
        name = names[counter];
        message = messages[counter];
        if (counter == 3)
            isReply = true;
        ++counter;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(500, 100));
        createMessage();
    }

    void createMessage() {
        add(new JLabel(name));
        JLabel label = new JLabel("<html>" + message + "</html>");
        add(label);
        label.setPreferredSize(new Dimension(500, 50));
        remove(getComponentCount() - 1);
        add(label);
    }
}
