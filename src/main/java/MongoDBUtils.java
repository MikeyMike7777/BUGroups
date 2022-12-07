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
        MongoCollection<Document> collection = database.getCollection("students");
        Bson filter = exists("TutorOffers");
        MongoCursor<Document> cursor = collection.find(filter).iterator();
        while (cursor.hasNext())
            System.out.println(cursor.next());

//        collection.deleteMany(filter);
//        StudentService ss = new StudentService();
//        ss.sendRegisterEmail("bryce_robinson1@baylor.edu", StudentService.generatePassword(8), "John Appleseed");
    }

}