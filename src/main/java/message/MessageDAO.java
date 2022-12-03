package message;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import student.Student;

import java.util.*;

import static com.mongodb.client.model.Filters.*;

class MessageDAO {

    // do we need these once we set up a controller? from *** here ***
    static ConnectionString connectionString = new ConnectionString(
            "mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli." +
                    "mongodb.net/?retryWrites=true&w=majority"
    );
    static MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    static MongoClient mongoClient = MongoClients.create(settings);
    static MongoDatabase database = mongoClient.getDatabase("test");
    // down to *** here ***

    private static MongoCursor<Document> cursor;
    static final String[] names = {
            "Biology and Health Sciences",
            "Business",
            "Chemistry and Biochemistry",
            "Education and Social Work",
            "Engineering and Computer Science",
            "English and Journalism",
            "Fine Arts",
            "Foreign Languages",
            "Geology and Environmental Science",
            "History and Political Science",
            "Math and Physics",
            "Philosophy and BIC",
            "Psychology and Sociology"
    };
    Vector<MessageBoard> boards;

    MessageDAO() {
        boards = new Vector<>();
        for (int i = 0; i < 12; ++i)
            boards.add(new MessageBoard(names[i], i));
    }

    void createMessage(String text, Student author, String courseNumber,
                       Integer board, Message message) {
        Message m = boards.elementAt(board).createMessage(text, author, courseNumber, message);
        MongoCollection<Document> collection = database.getCollection("BUGMessages");

        collection.insertOne(toDocument(m));
    }

    void deleteMessage(String id) {
        MongoCollection<Document> collection = database.getCollection("BUGMessages");
        Bson filter = eq("_id", id);
        collection.deleteOne(filter);
    }

    void editRepostMessage(String id, String text) {
        MongoCollection<Document> collection = database.getCollection("BUGMessages");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        Message message = toMessage(cursor.next());
        boards.elementAt(message.getBoard()).editRepostMessage(message, text);
    }

    static Message fetchMessage(String id) {
        MongoCollection<Document> collection = database.getCollection("BUGMessages");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        return toMessage(cursor.next());
    }

    Vector<Object> fetchBoard(Integer messageBoard) {
        MongoCollection<Document> collection = database.getCollection("BUGMessages");
        Bson filter = and(eq("messageBoard", messageBoard),
                eq("repliesTo", null));
        cursor = collection.find(filter).iterator();
        List<Message> messages = new ArrayList<>();
        while (cursor.hasNext())
            messages.add(toMessage(cursor.next()));
        Vector<Object> returning = new Vector<>(
                List.of(messages.stream()
                        .sorted(Comparator.comparing(Message::getTime).reversed())
                        .map(MessageDAO::toVector).toArray())
        );
        return returning;
    }

    static Vector<Object> toVector(Message message) {
        return new Vector<>(List.of(message.getAuthor(), message.getText(),
                message.getRepliesTo(), new Vector<>(List.of(message.getReplies()
                        .stream().sorted(Comparator.comparing(Message::getTime).reversed())
                        .map(MessageDAO::toVector)))));
    }

    static Document toDocument(Message message) {
        return new Document("_id", message.getID())
                .append("text", message.getText())
                .append("author", message.getAuthor().getID())
                .append("time", message.getTime())
                .append("courseNumber", message.getCourseNumber())
                .append("messageBoard", message.getBoard())
                .append("repliesTo", message.getRepliesTo() == null ?
                        null : message.getRepliesTo().getID())
                .append("replies", message.getReplies().stream()
                        .map(Message::getID).toArray());
    }

    static Message toMessage(Document document) {
        return new Message(document.getString("text"),
                (Student)document.get("author"), document.getString("courseNumber"),
                document.getInteger("messageBoard"), document.getDate("time"),
                List.of(((Collection<String>)(document.get("replies"))).stream()
                        .map(MessageDAO::fetchMessage).toArray(Message[]::new)),
                fetchMessage(document.getString("repliesTo")));
    }
}
