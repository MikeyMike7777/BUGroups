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
        this.id = author.getID() + time;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Student getAuthor() {
        return author;
    }

    public void setAuthor(Student author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Collection<Message> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Message> replies) {
        this.replies = replies;
    }

    public MessageBoard getBoard() {
        return board;
    }

    public void setBoard(MessageBoard board) {
        this.board = board;
    }

    public void setRepliesTo(Message repliesTo) {
        this.repliesTo = repliesTo;
    }

    public String getID() {
        return author.getID() + time;
    }
}
