package student;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

public class ForgetPasswordDAO {
    static ConnectionString connectionString = new ConnectionString(
            "mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli." +
                    "mongodb.net/?retryWrites=true&w=majority"
    );
    static MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    static MongoClient mongoClient = MongoClients.create(settings);
    //static MongoDatabase database = mongoClient.getDatabase("test");
    private static MongoCursor<Document> cursor;

    ForgetPasswordDAO() {

    }

    boolean verifyAccount(String email){
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = database.getCollection("profileInfos");
        Bson filter = Filters.eq("email", email);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return true;
        else return false;


//        collection.aggregate(
//                Arrays.asList(
//                        Aggregates.match(Filters.eq("email", "bill_booth1@baylor.edu"))
//                )
//        );
//        if(collection.)

    }

//
//    static Message fetchMessage(String id) {
//        MongoCollection<Document> collection = database.getCollection("BUGMessages");
//        Bson filter = eq("_id", id);
//        cursor = collection.find(filter).iterator();
//        if (cursor.hasNext())
//            return toMessage(cursor.next());
//        else return null;
//    }
//
//    List<Message> fetchBoard(Integer messageBoard) {
//        MongoCollection<Document> collection = database.getCollection("BUGMessages");
//        Bson filter = and(eq("messageBoard", messageBoard),
//                eq("repliesTo", "null"));
//        cursor = collection.find(filter).iterator();
//        List<Message> messages = new ArrayList<>();
//        while (cursor.hasNext())
//            messages.add(toMessage(cursor.next()));
//        return messages;
//    }
//
//    static Vector<Object> toVector(Message message) {
//        Vector<Object> v = new Vector<>(List.of(message.getAuthor(), message.getText(),
//                message.getRepliesTo(), message.getID(),
//                message.getReplies().size() == 0 ? new Vector<>(0) :
//                        new Vector<>(message.getReplies().stream()
//                                .sorted(Comparator.comparing(Message::getTime).reversed())
//                                .map(message.MessageDAO::toVector).collect(Collectors.toList()))));
//        return v;
//    }
//
//    static Document toDocument(Message message) {
//        return new Document("_id", message.getID())
//                .append("text", message.getText())
//                .append("author", message.getAuthor())
//                .append("time", message.getTime())
//                .append("courseNumber", message.getCourseNumber())
//                .append("messageBoard", message.getBoard())
//                .append("repliesTo", message.getRepliesTo())
//                .append("replies", asList(message.getReplies().stream()
//                        .map(Message::getID).toArray(String[]::new)));
//    }
//
//    static Message toMessage(Document document) {
//        return new Message(document.getString("text"),
//                document.getString("author"), document.getString("courseNumber"),
//                document.getInteger("messageBoard"), document.getDate("time"),
//                !(((ArrayList)document.get("replies")).equals(new ArrayList<>(0))) ?
//                        ((ArrayList<String>)(document.get("replies"))).stream()
//                                .map(message.MessageDAO::fetchMessage).filter(Objects::nonNull)
//                                .collect(Collectors.toList()) : (ArrayList)document.get("replies"),
//                document.getString("repliesTo"));
//    }

}
