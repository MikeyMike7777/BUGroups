package database.message;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class MessageService {
    MessageDAO mDAO = new MessageDAO();

    public Vector<Object> createMessage(String text, String author,
                       String courseNumber, Integer board, String message) {
        return mDAO.createMessage(text, author, courseNumber, board, message);
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

    public Vector<Object> fetchMessages(String id) {
        return new Vector<>(
                List.of(mDAO.fetchMessages(id).stream()
                        .sorted(Comparator.comparing(Message::getTime).reversed())
                        .map(MessageDAO::toVector).toArray())
        );
    }
}
