package message;

import student.Student;

import java.util.*;

public class Message {
    private String text;
    private Student author;
    private Date time;
    private String courseNumber;
    private Collection<Message> replies = new ArrayList<>();
    private Integer board;
    private Message repliesTo;
    private String id;

    Message(String text, Student author, String courseNumber,
            Integer board) {
        this.text = text;
        this.author = author;
        this.time = new Date();
        this.courseNumber = courseNumber;
        this.board = board;
        this.id = author.getID() + time;
    }

    Message(String text, Student author, String courseNumber,
            Integer board, Date time, List<Message> replies,
            Message repliesTo) {
        this(text, author, courseNumber, board);
        this.time = time;
        this.replies = replies;
        this.repliesTo = repliesTo;
    }

    void replyToMessage(Message message) {
        message.replies.add(this);
        this.repliesTo = message;
    }

    void delete() {
        this.repliesTo.replies.remove(this);
    }

    void edit(String text) {
        this.text = text;
    }

    boolean isReply() {
        return this.repliesTo != null;
    }

    public Message getRepliesTo() {
        return this.repliesTo;
    }

    public String getText() {
        return text;
    }

    public Student getAuthor() {
        return author;
    }

    Date getTime() {
        return time;
    }

    String getCourseNumber() {
        return courseNumber;
    }

    public Collection<Message> getReplies() {
        return replies;
    }

    Integer getBoard() {
        return board;
    }

    public String getID() {
        return id;
    }
}
