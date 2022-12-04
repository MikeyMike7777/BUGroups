package student;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Vector;

public class CourseDAO {
    ConnectionString connectionString = new ConnectionString("mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");
    private static MongoCursor<Document> cursor;

    void createCourse(String professor, Integer section, String courseCode, Vector<Student> students){
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

//    Vector<String> getClassmates(String courseCode){ // FIXME: doesn't work, i'm tired and want to sleep
//        Vector<String> classmatesInfo = new Vector<>();
//
//        // query for all students' profiles that have the given course code in their course list
//        MongoCollection<Document> collection = database.getCollection("courses");
//        Bson filter = eq("_id", courseCode);
//        cursor = collection.find(filter).iterator();
//
//        return classmatesInfo;
//    }
//
//    String toString(Document document){ // FIXME: doesn't work, i'm tired and want to sleep
//        document.getString("courseCode"), document.getString("professor"),
//    }
}
