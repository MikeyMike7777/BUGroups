package database.student;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.message.MessageService;
import database.utils.BUGUtils;
import database.utils.MongoInit;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.pull;

public class StudentDAO {

    private static MongoCursor<Document> cursor;

    /**
     * gets the courses that the username is apart of
     * @param username the username to search courses for
     * @return an arraylist of all the courses this username is in
     */
    ArrayList<String> getCourses(String username){ // given username
//        MongoCollection<Document> collection1 = BUGUtils.database.getCollection("BUGStudents");
//        Document courses = collection1.find(eq("_id", id)).first();
//        Vector<Object> s = new Vector<>();
//        s.addAll((Collection<?>) courses.get("courses"));
//        return s;

        // querying courses collection
        MongoCollection<Document> studentCollection = BUGUtils.database.getCollection("BUGStudents");
        // looking for course that matches courseId (course code + section)
        Document course = studentCollection.find(eq("_id", username)).first();
        // from the course Document object, return the list of Student IDs
        return (ArrayList<String>)(course.get("courses"));
    }

    /**
     * returns all the tutors that this username is being tutored by
     * @param username the username to search the database for
     * @return a vector of all the tutors this username has
     */
    Vector<String> getTutors(String username){
        MongoCollection<Document> collection1 = BUGUtils.database.getCollection("BUGStudents");
        Document student = collection1.find(eq("_id", username)).first();
        Vector<String> s = new Vector<>();
        try {
            s.addAll((Collection<? extends String>) student.get("tutors"));
        } catch(NullPointerException e){
            return s;
        }

        return s;
    }

    /**
     * registers a new student account
     * @param username the username of the account
     * @param password the password of the account
     * @return boolean representing whether creating the student was successful or not
     */
    boolean registerStudent(String username, String password){
        Student s = new Student(username, password);
        MongoCollection<Document> collection1 = BUGUtils.database.getCollection("BUGStudents");

        Document d1 = new Document("_id", s.getUsername())
                .append("username", s.getUsername())
                .append("password", s.getPassword())
                .append("courses", new ArrayList<String>())
                .append("tutors", new ArrayList<String>());


        collection1.insertOne(d1);

        return true;
    }

    /**
     * Fetches a students account information that matches id and returns as a student object
     * @param id the id to search for
     * @return the student object representing the information of id
     */
    Student fetchStudent(String id){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return toStudent(cursor.next());
        else return null;
    }

    /**
     * Fetches a students profile information that matches id and returns as a student object
     * @param id the id to search for
     * @return the profile object representing the information of id
     */
    Profile fetchProfile(String id){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext()){
            Document document = cursor.next();
            ArrayList<String> times = (ArrayList<String>) document.get("availability");
            Vector<String> convertTimes = new Vector<>();
            if(times != null) {
                convertTimes.addAll(times);
            }
            Availability availability = new Availability(convertTimes);

            return new Profile(document.getString("_id"), document.getString("name"),
                    document.getString("email"), document.getString("phoneNumber"), availability);
        }

        else return null;
    }

    /**
     * converts a document object to a student and returns it
     * @param document The document to convert
     * @return the Student object representing the document
     */
    public static Student toStudent(Document document) {
        ArrayList<String> c = (ArrayList<String>) document.get("courses");
        Vector<String> convertc = new Vector<>();
        if(c != null) {
            convertc.addAll(c);
        }

        ArrayList<String> t = (ArrayList<String>) document.get("tutors");
        Vector<String> convett = new Vector<>();
        if(t != null) {
            convett.addAll(t);
        }

        Vector<Course> courses = new Vector<>();
        for(int i = 0; i < convertc.size(); i++){
            courses.add(new Course(null, null, convertc.elementAt(i), null));
        }

        Vector<TutorOffer> tutors = new Vector<>();
        for(int i = 0; i < convett.size(); i++){
            tutors.add(new TutorOffer(convett.elementAt(i), null, null, null, null, null));
        }


        return new Student(document.getString("username"), document.getString("password"), courses, tutors);
    }

    /**
     * converts a Student object to a document and returns it
     * @param student The student to convert
     * @return the document object representing the student
     */
    public static Document toDocument(Student student) {
        Vector<String> courseList = new Vector<>();
        for(int i = 0; i < student.getCourses().size(); i++){
            courseList.add(student.getCourses().elementAt(i).getCourseCode());
        }

        Vector<String> tutorList = new Vector<>();
        for(int i = 0; i < student.getCourses().size(); i++){
            tutorList.add(student.getTutorOffers().elementAt(i).getCourseCode());
        }

        return new Document("_id", student.getID())
                .append("username", student.getUsername())
                .append("password", student.getPassword())
                .append("courses", courseList)
                .append("tutors", tutorList);
    }

    /**
     * changes the password of the account represented by ID to new password
     * @param ID the id to search for
     * @param password the password to change it to
     * @return the boolean representing whether it changed the password or not
     */
    boolean changePassword(String ID, String password){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = Filters.eq("_id", ID);
        cursor = collection.find(filter).iterator();

        if (cursor.hasNext()) {
            Bson update = set("password", password);
            collection.updateOne(filter, update);

            return true;
        }

        return false;
    }

    /**
     * Deletes an account that matches id
     * @param id the id to delete from database
     * @return the boolean representing whether the delete was successful or not
     */
    boolean deleteAccount(String id) {
        MongoCollection<Document> studentCollection = BUGUtils.database.getCollection("BUGStudents");
        MongoCollection<Document> profileCollection = BUGUtils.database.getCollection("profileInfos");
        MongoCollection<Document> messageCollection = BUGUtils.database.getCollection("BUGMessages");
        MongoCollection<Document> tutorCollection = BUGUtils.database.getCollection("tutors");

        MessageService ms = new MessageService();

        Bson filterStudentCollectionID = eq("_id", id);
        cursor = studentCollection.find(filterStudentCollectionID).iterator();
        if (cursor.hasNext()) {
            Document studentDocument = cursor.next();

            Bson filterAuthor = eq("author", studentDocument.get("username"));
            messageCollection.find(filterAuthor);
            cursor = messageCollection.find(filterAuthor).iterator();
            while(cursor.hasNext()){
                Document d = cursor.next();
                ms.deleteMessage(d.getString("_id"));
            }

            Bson filterUsername = eq("username", studentDocument.get("username"));
            tutorCollection.deleteMany(filterUsername);

            Bson filterProfileCollectionID = eq("_id", studentDocument.get("username"));
            profileCollection.findOneAndDelete(filterProfileCollectionID);

            Bson filter = Filters.eq("_id", id);
            studentCollection.findOneAndDelete(filter);

        }

        return true;
    }

    /**
     * Delete the account represented by email
     * @param email the email of the account to delete
     * @return the boolean representing whether the delete was successful or not
     */
    boolean deleteAccountByEmail(String email){
        MongoCollection<Document> studentCollection = BUGUtils.database.getCollection("BUGStudents");
        MongoCollection<Document> profileCollection = BUGUtils.database.getCollection("profileInfos");
        MongoCollection<Document> messageCollection = BUGUtils.database.getCollection("BUGMessages");
        MongoCollection<Document> tutorCollection = BUGUtils.database.getCollection("tutors");

        MessageService ms = new MessageService();
        String id = email.substring(0, email.length() - 11);

        Bson filterStudentCollectionID = eq("_id", id);
        cursor = studentCollection.find(filterStudentCollectionID).iterator();
        if (cursor.hasNext()) {
            Document studentDocument = cursor.next();

            Bson filterAuthor = eq("author", studentDocument.get("username"));
            messageCollection.find(filterAuthor);
            cursor = messageCollection.find(filterAuthor).iterator();
            while(cursor.hasNext()){
                Document d = cursor.next();
                ms.deleteMessage(d.getString("_id"));
            }

            Bson filterUsername = eq("username", studentDocument.get("username"));
            tutorCollection.findOneAndDelete(filterUsername);

            Bson filterProfileCollectionID = eq("_id", studentDocument.get("username"));
            profileCollection.findOneAndDelete(filterProfileCollectionID);

            Bson filter = Filters.eq("_id", id);
            studentCollection.findOneAndDelete(filter);

        }

        return true;
    }

    /**
     * adds a new class of courseID for user with username
     * @param username the username of the person to add to
     * @param courseId the courseId of the course to add
     */
    void addClass(String username, String courseId) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = Filters.eq("_id", username);
        Bson update = addToSet("courses", courseId);
        collection.findOneAndUpdate(filter, update);
    }

    /**
     * add a new tutor offer to user id of offer
     * @param id the id of the user to add to
     * @param offer the offer of the tutor offer
     */
    void addTutorOffer(String id, String offer) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = Filters.eq("_id", id);
        Bson update = addToSet("tutors", offer);
        collection.findOneAndUpdate(filter, update);
    }

    /**
     * removes a course from a user of type courseId
     * @param username the username of the person to remove course from
     * @param courseId the courseId to remove
     */
    static void removeCourse(String username, String courseId){
        MongoCollection<Document> studentCollection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = eq("_id", username);
        Bson update = pull("courses", courseId);
        studentCollection.updateOne(filter, update);
    }

    /**
     * removes a tutoring offer for user
     * @param username the username to remove from
     * @param courseCode the courseCode of the course to remove from
     */
    static void removeOffer(String username, String courseCode){
        MongoCollection<Document> studentCollection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = eq("_id", username);
        Bson update = pull("tutors", username + courseCode);
        studentCollection.updateOne(filter, update);
    }

    boolean confirmed() {
        MongoCollection<Document> studentCollection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = eq("_id", "confirmationBoolean");
        return (boolean)studentCollection.find(filter).first().get("confirmed");
    }

    Collection<String> getAllStudents() {
        MongoCollection<Document> studentCollection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = and(not(eq("_id", "confirmationBoolean")), exists("_id"));
        MongoCursor<Document> doc = studentCollection.find(filter).iterator();
        Collection<String> c = new Vector<>();
        while (doc.hasNext()) {
            c.add((String)doc.next().get("username"));
        }
        return c;
    }

    boolean isActive(String user) {
        MongoCollection<Document> studentCollection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = eq("_id", user);
        return (boolean)studentCollection.find(filter).first().get("active");
    }

    void setActive(String user, boolean b) {
        MongoCollection<Document> studentCollection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = eq("_id", user);
        Bson update = set("active", b);
        studentCollection.updateOne(filter, update);
    }

    void setActivitiesFalse() {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = exists("_id");
        Bson update = set("active", false);
        collection.updateMany(filter, update);
    }
}