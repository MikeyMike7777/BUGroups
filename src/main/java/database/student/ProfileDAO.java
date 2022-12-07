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


    /**
     * creates a profile with the specified info
     * @param id the id of the profile entry
     * @param name the name of the profile
     * @param email the email of the profile
     * @param phone the phone number of the profile
     * @param availability the availability of the entry
     */
    void createProfileInfo(String id,String name, String email, String phone, Vector<String> availability){
        Availability a = new Availability(availability);
        Profile p = new Profile(id, name, email, phone, a);
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");


        Document d = toDocument(p);
        collection.deleteOne(d);
        collection.insertOne(d);
    }

    /**
     * deletes a profile of the specified id
     * @param id the id to remove from the database
     */
    void deleteProfileInfo(String id){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        collection.findOneAndDelete(filter);
    }

    /**
     * Fetches the info of the profile that
     * matches id and returns it as a profile object
     * @param id the id to query for
     * @return the profile object with data for id
     */
    Vector<Object> fetchProfileInfo(String id){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        Profile p = null;
        if (cursor.hasNext())
             p = toProfile(cursor.next());

        Vector<Object> v = new Vector<>();

        if (p != null){
            Vector<String> s = p.getAvailability().getTimes();
            v.add(p.getName());
            v.add(p.getEmail());
            v.add(p.getPhoneNumber());
            v.add(s);
        }

        return v;
    }

    /**
     * updates a profile that matches id with the updated name
     * @param id the id to search for
     * @param name the new string name to update profile with
     */
    public void updateProfileName(String id, String name){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        Bson update = set("name", name);
        collection.updateOne(filter, update);
    }

    /**
     * updates profile availability that matches id with new availability
     * @param id the id to match
     * @param avail the new availability to update profile with
     */
    public void updateProfileAvail(String id, Vector<String> avail){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        Bson update = set("availability", avail);
        collection.updateOne(filter, update);
    }

    /**
     * updates profile phone number that matches id
     * @param id the id to match
     * @param number the new phone number to update profile with
     */
    public void updateProfilePhoneNumber(String id, String number){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        Bson update = set("phoneNumber", number);
        collection.updateOne(filter, update);
    }

    /**
     * Converts a profile object to a document object
     * @param profile the profile to convert
     * @return the document object representing the profile
     */
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

    /**
     * Converts a document to a profile
     * @param document the document to convert
     * @return the profile object representing the document
     */
    static Profile toProfile(Document document) {
        ArrayList<String> a = (ArrayList<String>) document.get("availability");
        Vector<String> convert = new Vector<>();

        if(a != null) {
            convert.addAll(a);
        }

        Availability avail = new Availability(convert);

        return new Profile(document.getString("_id"), document.getString("name"), document.getString("email"), document.getString("phoneNumber"), avail);
    }

    /**
     * get the information of the classmates in students
     * @param students the students whose information will be gotten
     * @return the vector of students information in the form of ArrayList<String>
     */
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
