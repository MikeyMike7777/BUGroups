package message;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import student.Student;

public class MessageDAO {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");

    void createMessage(String text, Student author, String time,
                       String courseNumber, Message message) {
        MessageBoard temp = new MessageBoard();
        Message m = temp.createMessage(text, author, time, courseNumber, message);
        MongoCollection<Document> collection = database.getCollection("BUGMessages");
        Document d = toDocument(m);

        collection.insertOne(d);
    }

    public static Document toDocument(Message message) {
        return new Document("_id", message.getID())
                .append("text", message.getText())
                .append("author", message.getAuthor().getID())
                .append("time", message.getTime())
                .append("courseNumber", message.getCourseNumber())
                .append("messageBoard", message.getBoard().getID())
                .append("repliesTo", message.getRepliesTo() == null ?
                        null : message.getRepliesTo().getID());
    }
}
