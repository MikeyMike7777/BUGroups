package student;

import message.Message;

import java.util.Vector;

public class Student {
    private String username;
    private String password;
    private Vector<Course> courses;
    private Vector<Message> sentMessages;
    private Vector<TutorOffer> offers;
    private Profile profile;

    public Student() {
        username = "testUser";
    }

    public void createAccount(String username, String password){
        // system operations has this as no parameters but i feel like there
        // should be username and password ?
    }

    public void deleteAccount(String username, String password){
        // system operations has this as no parameters but i feel like there
        // should be username and password ?
    }

    public void login(String username, String password){

    }

    public void addCourse(String professor, String classCode, Integer section){
        // system operations says "addClass" but our class name is Course
        // i.e. need to change system operations
    }

    public void removeCourse(){
        // system operations says "removeClass" but our class name is Course
        // i.e. need to change system operations
    }

    public void setPassword(String password){
        // design model has this in Profile but makes more sense in Student
        // need to update design model
    }

    public void resetPassword(){
        // design model has this in Profile but makes more sense in Student
        // need to update design model
    }

    public String getID() {
        return username;
    }

    public String getUsername() {
        return username;
    }
}
