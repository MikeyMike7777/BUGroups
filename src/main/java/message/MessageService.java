package message;

import student.Student;

import java.util.List;

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

    public List<Message> fetchBoard(Integer messageBoard) {
        return mDAO.fetchBoard(messageBoard);
    }
}
