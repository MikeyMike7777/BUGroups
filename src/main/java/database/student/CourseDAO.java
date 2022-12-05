package database.student;

import ui.general.Window;

import com.mongodb.client.*;
import database.utils.BUGUtils;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Vector;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;

public class CourseDAO {

    private static MongoCursor<Document> cursor;

    void createCourse(String professor, Integer section, String courseCode){
        Course c = new Course(professor, section, courseCode, new Vector<>());
        MongoCollection<Document> collection = BUGUtils.database.getCollection("courses");

        Document d = toDocument(c);

        collection.insertOne(d);
    }

    boolean fetchCourse(Integer section, String courseCode) {
        String id = courseCode + section;
        MongoCollection<Document> collection = BUGUtils.database.getCollection("courses");
        Bson filter = eq("_id", id);
        return collection.find(filter).iterator().hasNext();
    }

    void enroll(String id, Integer section, String courseCode) {
        String course = courseCode + section;
        MongoCollection<Document> collection = BUGUtils.database.getCollection("courses");
        Bson filter = eq("_id", course);
        Bson update = addToSet("students", id);

        collection.findOneAndUpdate(filter, update);
    }

    // Document is an object in the collection in the database
    public static Document toDocument(Course course) {
        return new Document("_id", course.getCourseCode() + course.getSection())
                .append("courseCode", course.getCourseCode())
                .append("professor", course.getProfessor())
                .append("section", course.getSection())
                .append("students", course.getStudents());
    }



    ArrayList<String> getStudents(String courseId){
        // query for all students' profiles that have the given course code in their course list
        // get all students in a course

        // querying courses collection
        MongoCollection<Document> courseCollection = BUGUtils.database.getCollection("courses");
        // looking for course that matches courseId (course code + section)
        Document course = courseCollection.find(eq("_id", courseId)).first();
        // from the course Document object, return the list of Student IDs
        return (ArrayList<String>)(course.get("students"));
    }

    // generates dummy data in course collection for testing classmates FIXME: remove when done testing
    void generate(){
        MongoCollection<Document> courseCollection = BUGUtils.database.getCollection("courses");
        if (courseCollection.countDocuments() > 0){
            // if there is stuff in the collection, delete everything
            courseCollection.deleteMany(new Document());
        }
        // course 1
        Vector<String> students1 = new Vector<>();
        students1.add("tomas_cerny1");
        students1.add("greg_hamerly1");
        students1.add("bill_booth1");
        createCourse("CSI prof", 1, "CSI 3471");

        // course 2
        Vector<String> students2 = new Vector<>();
        students2.add("bill_booth1");
        students2.add("cindy_fry1");
        students2.add("greg_speegle1");
        createCourse("WGS prof", 2, "WGS 2300");
    }
}
