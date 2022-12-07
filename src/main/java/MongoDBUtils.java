import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import database.student.StudentService;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.pull;

public class MongoDBUtils {

    public static void main(String [] args) {

        ConnectionString connectionString = new ConnectionString("mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = database.getCollection("BUGMessages");
        Bson filter = exists("_id");

        MongoCursor<Document> cursor = collection.find(filter).iterator();
//        Document d = new Document("_id", "john_appleseed1")
//                .append("name", "")
//                .append("email", "john_appleseed1@baylor.edu")
//                .append("phoneNumber", "")
//                .append("availability", new ArrayList<>());
//        collection.insertOne(d);
        while (cursor.hasNext())
            System.out.println(cursor.next());

//        collection.deleteMany(filter);
//        StudentService ss = new StudentService();
//        ss.sendRegisterEmail("bryce_robinson1@baylor.edu", StudentService.generatePassword(8), "John Appleseed");
    }

}