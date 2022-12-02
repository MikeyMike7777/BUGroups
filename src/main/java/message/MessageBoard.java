package message;

import student.Student;

import java.util.*;

public class MessageBoard {
    private String boardName;
    private Collection<Message> messages = new Vector<>();

    public void editRepostMessage(Message message, String text) {
        this.messages.remove(message);
        if (message.isReply())
            message.delete();
        message.edit(text);
        if (message.isReply())
            message.replyToMessage(message.getRepliesTo());
        this.messages.add(message);
    }

    public Message createMessage(String text, Student author, String time,
                              String courseNumber, Message message) {
        Message m = new Message(text, author, time, courseNumber, this);
        messages.add(m);
        if (message != null)
            m.replyToMessage(message);
        return m;
    }

    public int getID() {
        return 0;
    }
}
