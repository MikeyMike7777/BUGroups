package database.student;

import com.mongodb.client.MongoCollection;
import database.utils.BUGUtils;
import database.utils.MongoInit;
import org.bson.Document;

import java.util.Vector;

public class StudentDAO {

    void createStudent(String username, String password){
        Student s = new Student(username, password);
        MongoCollection<Document> collection = BUGUtils.database.getCollection("students");

        Document d = toDocument(s);

        collection.insertOne(d);
    }
    public static Document toDocument(Student student) {
        Vector<String> courseList = new Vector<>();
        for(int i = 0; i < student.getCourses().size(); i++){
            courseList.add(student.getCourses().elementAt(i).getCourseCode());
        }

        return new Document("_id", student.getID())
                .append("username", student.getUsername())
                .append("password", student.getPassword())
                .append("courses", courseList);
    }
}
