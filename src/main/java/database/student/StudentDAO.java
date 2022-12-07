package database.student;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.message.MessageService;
import database.utils.BUGUtils;
import database.utils.MongoInit;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.pull;

public class StudentDAO {

    private static MongoCursor<Document> cursor;

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

    // get all courses that a student has (their id is student who made it and date they made it)
    Vector<String> getTutors(String username){
        ArrayList<String> tutorOffers = new ArrayList<>();
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

    Student fetchStudent(String id){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return toStudent(cursor.next());
        else return null;
    }

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

    //Deletes everything in relation to a students account
    //therefore all the logic will be in student
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
            tutorCollection.findOneAndDelete(filterUsername);

            Bson filterProfileCollectionID = eq("_id", studentDocument.get("username"));
            profileCollection.findOneAndDelete(filterProfileCollectionID);

            Bson filter = Filters.eq("_id", id);
            studentCollection.findOneAndDelete(filter);

        }

        return true;
    }

    void addClass(String username, String courseId) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = Filters.eq("_id", username);
        Bson update = addToSet("courses", courseId);
        collection.findOneAndUpdate(filter, update);
    }

    void addTutorOffer(String id, String offer) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = Filters.eq("_id", id);
        Bson update = addToSet("tutors", offer);
        collection.findOneAndUpdate(filter, update);
    }

    // removes course from students' list of courses they're enrolled in
    static void removeCourse(String username, String courseId){
        MongoCollection<Document> studentCollection = BUGUtils.database.getCollection("BUGStudents");
        Bson filter = eq("_id", username);
        Bson update = pull("courses", courseId);
        studentCollection.updateOne(filter, update);
    }
}