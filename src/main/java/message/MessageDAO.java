package message;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;

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
        for (int i = 0; i < 13; ++i)
            boards.add(new MessageBoard(names[i], i));
    }

    void createMessage(String text, String author, String courseNumber,
                       Integer board, String message) {
        Message temp = null;
        if (!message.equals("null"))
            temp = fetchMessage(message);
        Message m = boards.elementAt(board).createMessage(text, author, courseNumber, temp);
        MongoCollection<Document> collection = database.getCollection("BUGMessages");
        Document d = toDocument(m);
        collection.insertOne(d);
        if (temp != null) {
            Bson filter = eq("_id", temp.getID());
            Bson update = addToSet("replies", m.getID());
            collection.updateOne(filter, update);
        }
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
        Message repliesTo = message.getRepliesTo().equals("null") ?
                null : fetchMessage(message.getRepliesTo());
        boards.elementAt(message.getBoard()).editRepostMessage(message, text, repliesTo);
    }

    static Message fetchMessage(String id) {
        MongoCollection<Document> collection = database.getCollection("BUGMessages");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return toMessage(cursor.next());
        else return null;
    }

    List<Message> fetchBoard(Integer messageBoard) {
        MongoCollection<Document> collection = database.getCollection("BUGMessages");
        Bson filter = and(eq("messageBoard", messageBoard),
                eq("repliesTo", "null"));
        cursor = collection.find(filter).iterator();
        List<Message> messages = new ArrayList<>();
        while (cursor.hasNext())
            messages.add(toMessage(cursor.next()));
        return messages;
    }

    static Vector<Object> toVector(Message message) {
        return new Vector<>(List.of(message.getAuthor(), message.getText(),
                message.getRepliesTo(), message.getID(),
                message.getReplies().size() == 0 ? new Vector<>(0) :
                        new Vector<>(List.of(message.getReplies().stream()
                                .sorted(Comparator.comparing(Message::getTime).reversed())
                                .map(MessageDAO::toVector)))));
    }

    static Document toDocument(Message message) {
        return new Document("_id", message.getID())
                .append("text", message.getText())
                .append("author", message.getAuthor())
                .append("time", message.getTime())
                .append("courseNumber", message.getCourseNumber())
                .append("messageBoard", message.getBoard())
                .append("repliesTo", message.getRepliesTo())
                .append("replies", asList(message.getReplies().stream()
                        .map(Message::getID).toArray(String[]::new)));
    }

    static Message toMessage(Document document) {
        return new Message(document.getString("text"),
                document.getString("author"), document.getString("courseNumber"),
                document.getInteger("messageBoard"), document.getDate("time"),
                List.of(((Collection<String>)(document.get("replies"))).stream()
                        .map(MessageDAO::fetchMessage).filter(Objects::nonNull)
                        .toArray(Message[]::new)),
                document.getString("repliesTo"));
    }
}
