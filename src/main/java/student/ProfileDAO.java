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


    void createProfileInfo(String id,String name, String email, String phone, Availability availability){
        Profile p = new Profile(id, name, email, phone, availability);
        MongoCollection<Document> collection = database.getCollection("profileInfos");
        Bson filter = eq("_id", id);

        Document d = toDocument(p);
        if(fetchProfileInfo(p.getId()) == null) {
            collection.insertOne(d);
        } else {
            collection.deleteOne(filter);
            collection.insertOne(d);
        }
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
        return new Document("_id", profile.getId())
                .append("name", profile.getName())
                .append("email", profile.getEmail())
                .append("phoneNumber", profile.getPhoneNumber())
                .append("availability", profile.getAvailability());
    }

    static Profile toProfile(Document document) {
        Availability a;
        a = (Availability) document.get("availability");
        return new Profile(document.getString("_id"), document.getString("name"), document.getString("email"), document.getString("phoneNumber"), a);
    }

    Vector<Vector<String>> getClassmates(Vector<String> students){
        Vector<Vector<String>> classmates = new Vector<>();

        // querying profileInfos collection
        MongoCollection<Document> profileCollection = database.getCollection("profileInfos");
        // for each student, get their name, email, phone number, and availability
        for (String id : students){
            Vector<String> classmateInfo = new Vector<>();
            // find student based on their username (id)
            Document student = profileCollection.find(eq("_id", id)).first();
            // add vector of that student's information to vector
            Profile profile = toProfile(student);
            classmateInfo.add(profile.getName());
            classmateInfo.add(profile.getEmail());
            classmateInfo.add(profile.getPhoneNumber());
            //FIXME: also need to add availability but availability class is being redone

            classmates.add(classmateInfo);
        }

        return classmates;
    }

}
