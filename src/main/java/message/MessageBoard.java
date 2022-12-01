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

    public void createMessage(String text, String author, String time,
                              String courseNumber, Student student, Message message) {
        Message m = new Message(text, author, time, courseNumber, this, student);
        messages.add(m);
        if (message != null)
            m.replyToMessage(message);
    }
}
