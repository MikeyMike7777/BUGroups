package student;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class AvailabilityDAO {

    // connection information
    ConnectionString connectionString = new ConnectionString("mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);

    // will need to change "test" later. test (database) contains our collections
    MongoDatabase database = mongoClient.getDatabase("test");

    // creates new availability entry in availability collection in our database
    void createAvailability(){
        MongoCollection<Document> collection = database.getCollection("Student");

    }

    /*
    notes:
    cursor is how we access collection objects
    if we're trying to access/modify objects we use a cursor
     */
}
