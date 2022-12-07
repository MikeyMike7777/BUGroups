import database.message.MessageService;
import database.student.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

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

//    @Test
//    void verifyValidAccount() {
//        assertTrue(studentService.verifyAccount("gabriel_goulis1@baylor.edu"));
//    }
//
//    @Test
//    void verifyInvalidAccount() {
//        assertTrue(studentService.verifyAccount("greg_hamerly1@baylor.edu"));
//    }
//
//
//
//    @Test
//    void testInvalidEmail(){
//        assertFalse(studentService.sendPasswordReset("invalidEmail.email!"));
//    }
//
//    @Test
//    void testValidEmail(){
//        assertTrue(studentService.sendPasswordReset("gabriel_goulis1@baylor.edu"));
//    }

    @Test
    void createAccount(){
        Student testStudent = new Student("TestUser", "TestPass", new Vector<Course>(), new Vector<TutorOffer>());
        Profile testProfile = new Profile("TestUser", "TestUserName", "Test@baylor.edu", "111-111-1111", new Availability(new Vector<>()));
        studentService.registerStudent(testStudent.getUsername(), testStudent.getPassword());
        studentService.createProfileInfo(testStudent.getUsername(), testProfile.getName(), testProfile.getEmail(), testProfile.getPhoneNumber(), testProfile.getAvailability().getTimes());

        assertTrue(studentService.verifyAccount(testProfile.getEmail()));

        Student returnStudent = studentService.getStudent(testStudent.getID());
        assertEquals(returnStudent, testStudent);

        Profile returnProfile = studentService.getProfile(testProfile.getId());
        assertEquals(returnProfile, testProfile);

        studentService.deleteAccount(testStudent.getID());

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
