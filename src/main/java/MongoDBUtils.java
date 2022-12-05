import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
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
        MongoCollection<Document> collection = database.getCollection("courses");
        Bson filter = exists("courses");
        Bson update = pull("students", "carsyn_smeda1");
//        Bson update2 = set("tutors", new ArrayList<String>());
        collection.updateMany(filter, update);
    }

}