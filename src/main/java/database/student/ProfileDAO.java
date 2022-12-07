package database.student;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import database.utils.BUGUtils;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.set;

public class ProfileDAO {

    private static MongoCursor<Document> cursor;


    void createProfileInfo(String id,String name, String email, String phone, Vector<String> availability){
        Availability a = new Availability(availability);
        Profile p = new Profile(id, name, email, phone, a);
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");


        Document d = toDocument(p);
        collection.deleteOne(d);
        collection.insertOne(d);
    }

    Profile fetchProfileInfo(String id){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return toProfile(cursor.next());
        else return null;
    }
    public void updateProfileName(String id, String name){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        Bson update = set("name", name);
        collection.updateOne(filter, update);
    }

    public void updateProfileAvail(String id, Vector<String> avail){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        Bson update = set("availability", avail);
        collection.updateOne(filter, update);
    }

    public void updateProfilePhoneNumber(String id, String number){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        Bson update = set("phoneNumber", number);
        collection.updateOne(filter, update);
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

    Vector<ArrayList<String>> getClassmates(ArrayList<String> students){
        Vector<ArrayList<String>> classmates = new Vector<>();

        // querying profileInfos collection
        MongoCollection<Document> profileCollection = BUGUtils.database.getCollection("profileInfos");
        // for each student, get their name, email, phone number, and availability
        for (String id : students){
            ArrayList<String> classmateInfo = new ArrayList<>();
            // find student based on their username (id)
            Document student = profileCollection.find(eq("_id", id)).first();
            // add vector of that student's information to vector
            Profile profile = toProfile(student);
            classmateInfo.add(profile.getName());
            classmateInfo.add(profile.getEmail());
            classmateInfo.add(profile.getPhoneNumber());
            classmateInfo.add(profile.getAvailability().toString());
            //System.out.println(profile.getAvailability().toString());

            classmates.add(classmateInfo);
        }

        return classmates;
    }
}
