package database.message;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class MessageService {
    MessageDAO mDAO = new MessageDAO();

    /**
     * creates a new message in database with the following information
     * @param text text of the message
     * @param author the author of the creator
     * @param courseNumber the course number
     * @param board the board the message is under
     * @param message the message
     * @return the Vector of objects
     */
    public Vector<Object> createMessage(String text, String author,
                       String courseNumber, Integer board, String message) {
        return mDAO.createMessage(text, author, courseNumber, board, message);
    }

    /**
     * deletes a message of id in the database
     * @param id the id that will be used to delete
     */
    public void deleteMessage(String id) {
        mDAO.deleteMessage(id);
    }

    /**
     * Edits a repost message in the database that matches id
     * @param id the id of the repost message
     * @param text the updated text of the repost message
     */
    public void editRepostMessage(String id, String text) {
        mDAO.editRepostMessage(id, text);
    }

    /**
     * Fetches a message that matches an id from the database
     * @param messageBoard the id of the message to fetch
     * @return returns a message object representing the message in the database
     */
    public Vector<Object> fetchBoard(Integer messageBoard) {
        return new Vector<>(
                List.of(mDAO.fetchBoard(messageBoard).stream()
                        .sorted(Comparator.comparing(Message::getTime).reversed())
                        .map(MessageDAO::toVector).toArray())
        );
    }

    /**
     * Fetches a list message replies that matches an id from the database
     * @param id the id of the message replies to fetch
     * @return returns a list of replies of the message of id
     */
    public Vector<Object> fetchMessages(String id) {
        return new Vector<>(
                List.of(mDAO.fetchMessages(id).stream()
                        .sorted(Comparator.comparing(Message::getTime).reversed())
                        .map(MessageDAO::toVector).toArray())
        );
    }
}
