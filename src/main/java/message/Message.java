package message;

import student.Student;

import java.util.*;

public class Message {
    private String text;
    private Student author;
    private String time;
    private String courseNumber;
    private Collection<Message> replies = new Vector<>();
    private MessageBoard board;
    private Message repliesTo;
    private String id;

    Message(String text, Student author, String time, String courseNumber,
            MessageBoard board) {
        this.text = text;
        this.author = author;
        this.time = time;
        this.courseNumber = courseNumber;
        this.board = board;
        this.id = author.getID() + Calendar.getInstance();
    }

    public void replyToMessage(Message message) {
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

    Message getRepliesTo() {
        return this.repliesTo;
    }

    String getText() {
        return text;
    }

    Student getAuthor() {
        return author;
    }

    String getTime() {
        return time;
    }

    String getCourseNumber() {
        return courseNumber;
    }

    Collection<Message> getReplies() {
        return replies;
    }

    MessageBoard getBoard() {
        return board;
    }

    public String getID() {
        return id;
    }
}
