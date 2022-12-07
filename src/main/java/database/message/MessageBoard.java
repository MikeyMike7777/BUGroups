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

    /**
     * Edit's a message and reposts
     * @param message the message that will be edited
     * @param text the updated text that will be inserted into message
     * @param repliesTo the message that this message replies to
     */
    public void editRepostMessage(Message message, String text, Message repliesTo) {
        this.messages.remove(message);
        if (repliesTo != null)
            repliesTo.delete(message);
        message.edit(text);
        if (message.isReply() && repliesTo != null)
            message.replyToMessage(repliesTo);
        this.messages.add(message);
    }

    /**
     * Creates a new message thread on message board
     * @param text the text that this message will have
     * @param author the author of this message
     * @param courseNumber the courseNumber this message falls under
     * @param message the message object that this message is represented by
     * @return the message that was posted on message board
     */
    public Message createMessage(String text, String author,
                              String courseNumber, Message message) {
        Message m = new Message(text, author, courseNumber, this.index,
                message == null ? "null" : message.getID());
        messages.add(m);
        return m;
    }
}
