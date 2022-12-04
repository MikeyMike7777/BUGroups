package student;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import ui.general.Window;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class ProfileDAO {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");

    private static MongoCursor<Document> cursor;


    void createProfileInfo(String id,String name, String email, String phone, Vector<String> availability){
        Availability a = new Availability(availability);
        Profile p = new Profile(id, name, email, phone, a);
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
        ArrayList<String> a = new ArrayList<>();
        if(profile.getAvailability().getTimes() != null) {
            a.addAll(profile.getAvailability().getTimes());
        }
        return new Document("_id", profile.getId())
                .append("name", profile.getName())
                .append("email", profile.getEmail())
                .append("phoneNumber", profile.getPhoneNumber())
                .append("availability", a);
    }

    static Profile toProfile(Document document) {
        ArrayList<String> a = (ArrayList<String>) document.get("availability");
        Vector<String> convert = new Vector<>();

        convert.addAll(a);

        Availability avail = new Availability(convert);

        return new Profile(document.getString("_id"), document.getString("name"), document.getString("email"), document.getString("phoneNumber"), avail);
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
