package database.student;

import com.mongodb.client.*;
import database.utils.BUGUtils;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Vector;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.pull;

public class CourseDAO {

    private static MongoCursor<Document> cursor;

    /**
     * Creates a course entry in the database
     * @param id the id of the course
     * @param courseCode the course code
     * @param section the section of the course
     * @param professor the professor name
     */
    void createCourse(String id, String courseCode, String section, String professor){

        Course c = new Course(courseCode, section, professor, new Vector<>());
        MongoCollection<Document> collection = BUGUtils.database.getCollection("courses");

        Document d = toDocument(c);

        collection.insertOne(d);
    }

    /**
     * Checks if a course exists in the database or not
     * @param courseId the id of the course to search for
     * @return a boolean representing whether the course exists
     */
    boolean fetchCourse(String courseId) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("courses");
        Bson filter = eq("_id", courseId);
        return collection.find(filter).iterator().hasNext();
    }

    /**
     * Enrolls a user in a course
     * @param username the username of the user to enter
     * @param courseId the id of the course to enter them in
     */
    void enroll(String username, String courseId) {
        MongoCollection<Document> collection = BUGUtils.database.getCollection("courses");
        Bson filter = eq("_id", courseId);
        Bson update = addToSet("students", username);

        collection.findOneAndUpdate(filter, update);
    }

    /**
     * Converts a course object into a document object and returns it
     * @param course the course object to convert
     * @return the document representing the course to return
     */
    public static Document toDocument(Course course) {
        return new Document("_id", course.getCourseCode().toUpperCase().replace(" ", "") + course.getSection())
                .append("courseCode", course.getCourseCode())
                .append("professor", course.getProfessor())
                .append("section", course.getSection())
                .append("students", course.getStudents());
    }

    /**
     * Gets a list of students in a course other than the student with username
     * @param courseId the id of the course to query for
     * @param username the username of the user querying
     * @return a list representing all other students in specified course
     */
    ArrayList<String> getStudents(String courseId, String username){
        // query for all students' profiles that have the given course code in their course list
        // get all students in a course

        ArrayList<String> students = new ArrayList<>();

        // querying courses collection
        MongoCollection<Document> courseCollection = BUGUtils.database.getCollection("courses");
        // looking for course that matches courseId (course code + section)
        Document course = courseCollection.find(eq("_id", courseId)).first();
        // from the course Document object, return the list of Student IDs
        students = (ArrayList<String>)(course.get("students"));
        students.remove(username);

        return students;
    }

    // removes student from course's list of students that are enrolled in it

    /**
     * removes student from course's list of students that are enrolled in it
     * @param username the username of the user to remove
     * @param courseId the id of the course to remove them from
     */
    static void removeCourse(String username, String courseId){
        MongoCollection<Document> courseCollection = BUGUtils.database.getCollection("courses");
        Bson filter = eq("_id", courseId);
        Bson update = pull("students", username);
        courseCollection.updateOne(filter, update);
    }
}
