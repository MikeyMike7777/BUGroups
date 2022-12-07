package database.message;

import java.util.*;

public class Message {
    private String text;
    private String author;
    private Date time;
    private String courseNumber;
    private List<Message> replies = new ArrayList<>();
    private Integer board;
    private String repliesTo;
    private String id;

    Message(String text, String author, String courseNumber,
            Integer board, String repliesTo) {
        this.text = text;
        this.author = author;
        this.time = new Date();
        this.courseNumber = courseNumber;
        this.board = board;
        this.id = author + time;
        this.repliesTo = repliesTo;
    }

    Message(String text, String author, String courseNumber,
            Integer board, Date time, List<Message> replies,
            String repliesTo, String id) {
        this(text, author, courseNumber, board, repliesTo);
        this.time = time;
        this.replies = replies;
        this.id = id;
    }

    /**
     * Allows this message to be added as a reply to another message
     * to form a thread of messages
     * @param message Message that will be added
     */
    void replyToMessage(Message message) {
        message.replies.add(this);
        this.repliesTo = message.id;
    }

    /**
     * Deletes a reply from this messages replies'
     * @param message Message that will be deleted from replies
     */
    void delete(Message message) {
        this.replies.remove(message);
    }

    /**
     * Edits a message's text
     * @param text New text that will be inserted into object
     */
    void edit(String text) {
        this.text = text;
    }

    /**
     * Returns a boolean value representing whether this message is
     * a reply
     * @return boolean representing if this message is a reply
     */
    boolean isReply() {
        return !this.repliesTo.equals("null");
    }

    String getRepliesTo() {
        return this.repliesTo;
    }

    String getText() {
        return text;
    }

    String getAuthor() {
        return author;
    }

    Date getTime() {
        return time;
    }

    String getCourseNumber() {
        return courseNumber;
    }

    Collection<Message> getReplies() {
        return replies;
    }

    Integer getBoard() {
        return board;
    }

    String getID() {
        return id;
    }

}
