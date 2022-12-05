package database.student;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import database.utils.BUGUtils;
import database.utils.MongoInit;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Vector;

import static com.mongodb.client.model.Filters.eq;

public class StudentDAO {

    private static MongoCursor<Document> cursor;

    void createStudent(String id, String username, String password, Vector<String> courses, Vector<String> tutors){
        Student s = new Student(username, password);
        MongoCollection<Document> collection = BUGUtils.database.getCollection("students");

        Document d = toDocument(s);

        collection.insertOne(d);
    }

    Student fetchStudent(String id){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return toStudent(cursor.next());
        else return null;
    }

    static Student toStudent(Document document) {
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



}
