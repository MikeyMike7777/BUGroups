package message;

import student.Student;

public class MessageService {
    MessageDAO messageDAO = new MessageDAO();

    public void createMessage(String text, Student author, String time,
                       String courseNumber, Message message) {
        messageDAO.createMessage(text, author, time, courseNumber, message);
    }
}
