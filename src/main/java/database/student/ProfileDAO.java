package database.student;

import com.mongodb.client.*;
import database.utils.BUGUtils;
import database.utils.MongoInit;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class ProfileDAO {

    private static MongoCursor<Document> cursor;


    void createProfileInfo(String id,String name, String email, String phone, Vector<String> availability){
        Availability a = new Availability(availability);
        Profile p = new Profile(id, name, email, phone, a);
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
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
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
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
        MongoCollection<Document> profileCollection = BUGUtils.database.getCollection("profileInfos");
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

    // generates dummy data in profile collection for testing classmates FIXME: remove when done testing
    // id is username
    void generate(){
        MongoCollection<Document> profileCollection = BUGUtils.database.getCollection("profileInfos");
        if (profileCollection.countDocuments() > 0){
            // if there is stuff in the collection, delete everything
            profileCollection.deleteMany(new Document());
        }
        Vector<String> times = new Vector<>();
        times.add("Monday 5:00-6:00");
        Availability a = new Availability(times);
//        createProfileInfo("tomas_cerny1", "Tomas Cerny", "tomas_cerny1@baylor.edu", "254-900-1852", a);
//        createProfileInfo("greg_hamerly1", "Greg Hamerly", "greg_hamerly1@baylor.edu", "708-351-5325", a);
//        createProfileInfo("bill_booth1", "Bill Booth", "bill_booth1@baylor.edu", "396-135-9223", a);
    }

}
