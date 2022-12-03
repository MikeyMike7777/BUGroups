package message;

import student.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class MessageService {
    MessageDAO mDAO = new MessageDAO();

    public void createMessage(String text, String author,
                       String courseNumber, Integer board, String message) {
        mDAO.createMessage(text, author, courseNumber, board, message);
    }

    public void deleteMessage(String id) {
        mDAO.deleteMessage(id);
    }

    public void editRepostMessage(String id, String text) {
        mDAO.editRepostMessage(id, text);
    }

    public Vector<Object> fetchBoard(Integer messageBoard) {
        return new Vector<>(
                List.of(mDAO.fetchBoard(messageBoard).stream()
                        .sorted(Comparator.comparing(Message::getTime).reversed())
                        .map(MessageDAO::toVector).toArray())
        );
    }
}
