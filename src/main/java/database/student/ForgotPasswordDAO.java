package database.student;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ForgotPasswordDAO {
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

    ForgotPasswordDAO() {

    }

    /**
     * verify if an account of type email is already in the database
     * @param email the email to search for
     * @return boolean representing if the user already exists or not
     */
    boolean verifyAccount(String email) {
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = database.getCollection("profileInfos");
        Bson filter = Filters.eq("email", email);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return true;
        else return false;

    }

}
