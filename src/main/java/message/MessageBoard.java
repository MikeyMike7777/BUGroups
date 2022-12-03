package message;

import student.Student;

import java.util.*;

class MessageBoard {
    private String boardName;
    private Integer index;
    private Collection<Message> messages;

    MessageBoard(String boardName, Integer index) {
        this.boardName = boardName;
        this.index = index;
        this.messages = new ArrayList<>();
    }

    public void editRepostMessage(Message message, String text) {
        this.messages.remove(message);
        if (message.isReply())
            message.delete();
        message.edit(text);
        if (message.isReply())
            message.replyToMessage(message.getRepliesTo());
        this.messages.add(message);
    }

    public Message createMessage(String text, Student author,
                              String courseNumber, Message message) {
        Message m = new Message(text, author, courseNumber, this.index);
        messages.add(m);
        if (message != null)
            m.replyToMessage(message);
        return m;
    }

    public int getID() {
        return 0;
    }
}
