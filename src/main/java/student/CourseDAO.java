package student;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Vector;

import static com.mongodb.client.model.Filters.eq;

public class CourseDAO {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");
    private static MongoCursor<Document> cursor;

    void createCourse(String professor, Integer section, String courseCode, Vector<String> students){
        Course c = new Course(professor, section, courseCode, students);
        MongoCollection<Document> collection = database.getCollection("courses");

        Document d = toDocument(c);

        collection.insertOne(d);
    }

    // Document is an object in the collection in the database
    public static Document toDocument(Course course) {
        return new Document("_id", course.getCourseCode() + course.getSection())
                .append("courseCode", course.getCourseCode())
                .append("professor", course.getProfessor())
                .append("section", course.getSection())
                .append("students", course.getStudents());
    }

    Vector<String> getStudents(String courseId){
        // query for all students' profiles that have the given course code in their course list
        // get all students in a course

        // querying courses collection
        MongoCollection<Document> courseCollection = database.getCollection("courses");
        // looking for course that matches courseId (course code + section)
        Document course = courseCollection.find(eq("_id", courseId)).first();
        // from the course Document object, return the list of Student IDs
        return (Vector<String>)(course.get("students"));
    }

    // generates dummy data in course collection for testing classmates FIXME: remove when done testing
    void generate(){
        MongoCollection<Document> courseCollection = database.getCollection("courses");
        if (courseCollection.countDocuments() > 0){
            // if there is stuff in the collection, delete everything
            courseCollection.deleteMany(new Document());
        }
        Vector<String> students = new Vector<>();
        students.add("tomas_cerny1");
        students.add("greg_hamerly1");
        students.add("bill_booth1");
        createCourse("Prof Name", 1, "CSI 3471", students);
    }
}
