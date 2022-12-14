package database.student;

import database.message.Message;

import java.util.Objects;
import java.util.Vector;

public class Student {
    private String username;
    private String password;
    private Vector<Course> courses = new Vector<>(0);
    private Vector<TutorOffer> tutorOffers = new Vector<>(0);
    private Vector<Message> sentMessages;
    private Profile profile;

    public Student() {
        username = "testUser";
    }

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Student(String username, String password, Vector<Course> courses, Vector<TutorOffer> tutorOffers) {
        this.username = username;
        this.password = password;
        this.courses = courses;
        this.tutorOffers = tutorOffers;
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

    void setPassword(String password){
        this.password = password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Vector<Course> getCourses() {
        return courses;
    }

    public void setCourses(Vector<Course> courses) {
        this.courses = courses;
    }

    public Vector<TutorOffer> getTutorOffers() {
        return tutorOffers;
    }

    public void setTutorOffers(Vector<TutorOffer> tutorOffers) {
        this.tutorOffers = tutorOffers;
    }

    /**
     * Converts student object to a vector of objects and returns it
     * @return the Vector of objects this is represented by
     */
    public Vector<Object> toVector(){
        Vector<Object> studentInfo = new Vector<>();
        studentInfo.add(this.getUsername());
        studentInfo.add(this.getPassword());
        //courses?
        return studentInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(username, student.username) && Objects.equals(password, student.password) && Objects.equals(courses, student.courses) && Objects.equals(tutorOffers, student.tutorOffers) && Objects.equals(sentMessages, student.sentMessages) && Objects.equals(profile, student.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, courses, tutorOffers, sentMessages, profile);
    }
}
