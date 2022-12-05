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
            String repliesTo) {
        this(text, author, courseNumber, board, repliesTo);
        this.time = time;
        this.replies = replies;
        this.id = author + time;
    }

    void replyToMessage(Message message) {
        message.replies.add(this);
        this.repliesTo = message.id;
    }

    void delete(Message message) {
        this.replies.remove(message);
    }

    void edit(String text) {
        this.text = text;
    }

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
