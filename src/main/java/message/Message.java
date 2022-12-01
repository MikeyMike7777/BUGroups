package message;

import student.Student;

import java.util.*;

public class Message {
    private String text;
    private String author;
    private String time;
    private String courseNumber;
    private Collection<Message> replies = new Vector<>();
    private MessageBoard board;
    private Student student;

    Message(String text, String author, String time, String courseNumber,
            MessageBoard board, Student student) {
        this.text = text;
        this.author = author;
        this.time = time;
        this.courseNumber = courseNumber;
        this.board = board;
        this.student = student;
    }

    public void replyToMessage(Message message) {
        message.replies.add(this);
    }
}
