package student;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import ui.general.Window;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import static com.mongodb.client.model.Filters.eq;

public class ProfileDAO {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");

    private static MongoCursor<Document> cursor;


    void createProfileInfo(String name, String email, String phone, Availability availability){
        Profile p = new Profile(name, email, phone, availability);
        MongoCollection<Document> collection = database.getCollection("profileInfos");

        Document d = toDocument(p);

        collection.insertOne(d);
    }

    Profile fetchProfileInfo(String id){
        MongoCollection<Document> collection = database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return toProfile(cursor.next());
        else return null;
    }

    public static Document toDocument(Profile profile) {
        return new Document("_id", "username")
                .append("name", profile.getName())
                .append("email", profile.getEmail())
                .append("phoneNumber", profile.getPhoneNumber())
                .append("availability", profile.getAvailability());
    }

    static Profile toProfile(Document document) {
        Availability a;
        a = (Availability) document.get("availability");
        return new Profile(document.getString("name"), document.getString("email"), document.getString("phoneNumber"), a);
    }


}
