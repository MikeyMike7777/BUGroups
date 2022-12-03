package message;

import student.Student;

import java.util.Vector;

public class MessageService {
    MessageDAO mDAO = new MessageDAO();

    public void createMessage(String text, Student author,
                       String courseNumber, Integer board, Message message) {
        mDAO.createMessage(text, author, courseNumber, board, message);
    }

    public void deleteMessage(String id) {
        mDAO.deleteMessage(id);
    }

    public void editRepostMessage(String id, String text) {
        mDAO.editRepostMessage(id, text);
    }

    public Vector<Object> fetchBoard(Integer messageBoard) {
        return mDAO.fetchBoard(messageBoard);
    }
}
