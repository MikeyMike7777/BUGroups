package database.message;

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

    public void editRepostMessage(Message message, String text, Message repliesTo) {
        this.messages.remove(message);
        if (repliesTo != null)
            repliesTo.delete(message);
        message.edit(text);
        if (message.isReply() && repliesTo != null)
            message.replyToMessage(repliesTo);
        this.messages.add(message);
    }

    public Message createMessage(String text, String author,
                              String courseNumber, Message message) {
        Message m = new Message(text, author, courseNumber, this.index,
                message == null ? "null" : message.getID());
        messages.add(m);
        return m;
    }

    public int getID() {
        return index;
    }
}
