package database.student;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.utils.BUGUtils;
import database.utils.MongoInit;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Vector;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.set;

public class StudentDAO {

    private static MongoCursor<Document> cursor;

    public boolean registerStudent(String username, String password, String name, String email, String phone){
        Student s = new Student(username, password);
        MongoCollection<Document> collection1 = BUGUtils.database.getCollection("BUGStudents");
        MongoCollection<Document> collection2 = BUGUtils.database.getCollection("profileInfos");

        Document d1 = new Document("_id", s.getUsername())
                .append("username", s.getUsername())
                .append("password", s.getPassword())
                .append("courses", null)
                .append("tutors", null);

        collection1.insertOne(d1);

        Document d2 = new Document("_id", s.getUsername())
                .append("name", name)
                .append("email", email)
                .append("phoneNumber", phone)
                .append("availability", null);

        collection2.insertOne(d2);

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

    public static Student toStudent(Document document) {
        ArrayList<String> c = (ArrayList<String>) document.get("courses");
        Vector<String> convertc = new Vector<>();
        convertc.addAll(c);

        ArrayList<String> t = (ArrayList<String>) document.get("tutors");
        Vector<String> convett = new Vector<>();
        convett.addAll(t);

        Vector<Course> courses = new Vector<>();
        for(int i = 0; i < convertc.size(); i++){
            courses.add(new Course(null, null, convertc.elementAt(i), null));
        }

        Vector<TutorOffer> tutors = new Vector<>();
        for(int i = 0; i < convett.size(); i++){
            tutors.add(new TutorOffer(convett.elementAt(i), null, null, null));
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
            tutorList.add(student.getTutorOffers().elementAt(i).getCourse());
        }

        return new Document("_id", student.getID())
                .append("username", student.getUsername())
                .append("password", student.getPassword())
                .append("courses", courseList)
                .append("tutors", tutorList);
    }

    public boolean changePassword(String ID, String password){
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




}
