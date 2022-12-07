import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import database.message.MessageService;
import database.student.*;
import database.utils.BUGUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static com.mongodb.client.model.Filters.eq;
import static org.junit.Assert.*;

public class UnitTests {
    private StudentService studentService;
    private MessageService messageService;



//    @Test
//    void createStudent(){
//
//        Vector<Student> students = new Vector<Student>().add(new Student("TestUser1", "TestPass1", new Vector<Course>(), new Vector<TutorOffer>()));
//
//        final Vector<Course> courses = new Vector<>()
//                .add(new Course("CSI2334", "1", "Dr. Cerny"
//                        , ))));
//        final Vector<TutorOffer> tutorOffers;
//
//        final Student testStudent = new Student("TestUser2", "TestPass2",);
//    }

    @BeforeEach
    void init() {
        studentService = new StudentService();
        messageService = new MessageService();
    }


    @Test
    void deleteAccount() {
        studentService.deleteAccount("TestUser");
    }

    @Test
    void verifyValidAccount() {
        assertTrue(studentService.verifyAccount("gabriel_goulis1@baylor.edu"));
    }

    @Test
    void verifyInvalidAccount() {
        assertTrue(studentService.verifyAccount("greg_hamerly1@baylor.edu"));
    }



    @Test
    void testInvalidEmail(){
        assertFalse(studentService.sendPasswordReset("invalidEmail.email!"));
    }

    @Test
    void testValidEmail(){
        assertTrue(studentService.sendPasswordReset("gabriel_goulis1@baylor.edu"));
    }


    @Test
    void fetchStudent(){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGStudents");
        Document testStudent = new Document("_id", "TestUser");
        Vector<Object> student = new Vector<>();

        student.add("TestUser");
        student.add("TestPass");
        student.add(new Vector<Course>());
        student.add(new Vector<TutorOffer>());

        testStudent.append("username", "TestUser");
        testStudent.append("password", "TestPass");
        testStudent.append("courses", new Vector<Course>());
        testStudent.append("tutors", new Vector<TutorOffer>());

        Bson filter = eq("_id", testStudent.get("_id"));
        collection.deleteOne(filter);
        collection.insertOne(testStudent);

        Vector<Object> returnStudent = studentService.fetchStudent(String.valueOf(testStudent.get("_id")));

        assertEquals(student.size(), returnStudent.size());
        for (int i = 0; i < student.size(); i++) {
            assertEquals(student.get(i), returnStudent.get(i));
        }

        collection.deleteOne(filter);
    }

    @Test
    void deleteStudent(){
        MongoCursor<Document> cursor;
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGStudents");
        Document testStudent = new Document("_id", "TestUser");

        testStudent.append("username", "TestUser");
        testStudent.append("password", "TestPass");
        testStudent.append("courses", new Vector<Course>());
        testStudent.append("tutors", new Vector<TutorOffer>());

        Bson filter = eq("_id", testStudent.get("_id"));
        collection.deleteOne(filter);
        collection.insertOne(testStudent);

        studentService.deleteAccount(testStudent.getString("_id"));
        cursor = collection.find(filter).iterator();
        assertFalse(cursor.hasNext());

    }

    @Test
    void fetchProfile(){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Document testProfile = new Document("_id", "TestUser");
        Vector<Object> profile = new Vector<>();

        profile.add("Test User");
        profile.add("TestUser@baylor.edu");
        profile.add("111-111-1111");
        profile.add(new Vector<>());

        testProfile.append("name", "Test User");
        testProfile.append("email", "TestUser@baylor.edu");
        testProfile.append("phoneNumber", "111-111-1111");
        testProfile.append("availability", new Vector<>());

        Bson filter = eq("_id", testProfile.get("_id"));
        collection.deleteOne(filter);
        collection.insertOne(testProfile);

        Vector<Object> returnProfile = studentService.fetchProfileInfo(String.valueOf(testProfile.get("_id")));

        assertEquals(profile.size(), returnProfile.size());
        for (int i = 0; i < profile.size(); i++) {
            assertEquals(profile.get(i), returnProfile.get(i));
        }

        collection.deleteOne(filter);
    }

    @Test
    void deleteProfile(){
        MongoCursor<Document> cursor;
        MongoCollection<Document> collection = BUGUtils.database.getCollection("profileInfos");
        Document testProfile = new Document("_id", "TestUser");

        testProfile.append("name", "Test User");
        testProfile.append("email", "TestUser@baylor.edu");
        testProfile.append("phoneNumber", "111-111-1111");
        testProfile.append("availability", new Vector<>());

        Bson filter = eq("_id", testProfile.get("_id"));
        collection.deleteOne(filter);
        collection.insertOne(testProfile);

        studentService.deleteProfileInfo(testProfile.getString("_id"));
        cursor = collection.find(filter).iterator();
        assertFalse(cursor.hasNext());

    }


//    @Test
//    void createAccount(){
//
//    }
//
//    @Test
//    void deleteAccount(){
//
//    }
//
//    @Test
//    void postMessage(){
//
//    }
//
//    @Test
//    void deleteMessage(){
//
//    }
//
//    @Test
//    void addReply(){
//
//    }
//
//    @Test
//    void addCourse(){
//
//    }
//
//    @Test
//    void deleteCourse(){
//
//    }

}
