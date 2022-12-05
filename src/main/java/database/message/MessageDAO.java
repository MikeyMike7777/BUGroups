package database.message;

import com.mongodb.client.*;
import database.utils.BUGUtils;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;

class MessageDAO {

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

    Vector<Object> createMessage(String text, String author, String courseNumber,
                       Integer board, String message) {
        Message temp = null;
        if (!message.equals("null"))
            temp = fetchMessage(message);
        Message m = boards.elementAt(board).createMessage(text, author, courseNumber, temp);
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGMessages");
        Document d = toDocument(m);
        collection.insertOne(d);
        if (temp != null) {
            Bson filter = eq("_id", temp.getID());
            Bson update = addToSet("replies", m.getID());
            collection.updateOne(filter, update);
        }
        return toVector(m);
    }

    void deleteMessage(String id) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGMessages");
        Bson filter = eq("_id", id);
        Bson filter2 = eq("repliesTo", id);
        collection.deleteOne(filter);
        collection.deleteMany(filter2);
    }

    void editRepostMessage(String id, String text) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGMessages");
        Bson filter = eq("_id", id);
        MongoCursor<Document> cursor = collection.find(filter).iterator();
        Message message = toMessage(cursor.next());
        Message repliesTo = message.getRepliesTo().equals("null") ?
                null : fetchMessage(message.getRepliesTo());
        Bson update = set("text", text);
        collection.updateOne(filter, update);
        Date d = new Date();
        update = set("time", d);
        collection.updateOne(filter, update);
        boards.elementAt(message.getBoard()).editRepostMessage(message, text, repliesTo);
    }

    static Message fetchMessage(String id) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGMessages");
        Bson filter = eq("_id", id);
        MongoCursor<Document> cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return toMessage(cursor.next());
        else return null;
    }

    List<Message> fetchMessages(String id) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGMessages");
        Bson filter = eq("author", id);
        MongoCursor<Document> cursor = collection.find(filter).iterator();
        List<Message> messages = new ArrayList<>();
        while (cursor.hasNext())
            messages.add(toMessage(cursor.next()));
        return messages;
    }

    List<Message> fetchBoard(Integer messageBoard) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGMessages");
        Bson filter = and(eq("messageBoard", messageBoard),
                eq("repliesTo", "null"));
        MongoCursor<Document> cursor = collection.find(filter).iterator();
        List<Message> messages = new ArrayList<>();
        while (cursor.hasNext())
            messages.add(toMessage(cursor.next()));
        return messages;
    }

    static Vector<Object> toVector(Message message) {
        return  new Vector<>(List.of(message.getAuthor(), message.getText(),
                message.getRepliesTo(), message.getID(),
                message.getReplies().size() == 0 ? new Vector<>(0) :
                        new Vector<>(message.getReplies().stream()
                                .sorted(Comparator.comparing(Message::getTime))
                                .map(MessageDAO::toVector).collect(Collectors.toList())),
                message.getCourseNumber(), message.getTime(), message.getBoard()));
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
                !(((ArrayList)document.get("replies")).equals(new ArrayList<>(0))) ?
                ((ArrayList<String>)(document.get("replies"))).stream()
                        .map(MessageDAO::fetchMessage).filter(Objects::nonNull)
                        .collect(Collectors.toList()) : (ArrayList)document.get("replies"),
                document.getString("repliesTo"), document.getString("_id"));
    }
}
