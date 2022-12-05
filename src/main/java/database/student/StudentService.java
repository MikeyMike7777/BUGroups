package database.student;

import java.util.ArrayList;
import java.util.Vector;

public class StudentService {

    StudentDAO studentDAO = new StudentDAO();
    CourseDAO  courseDAO = new CourseDAO();
    ProfileDAO profileDAO = new ProfileDAO();
    TutorOfferDAO tutorOfferDAO = new TutorOfferDAO();
    ForgotPasswordDAO forgotPasswordDAO = new ForgotPasswordDAO();


    public void createProfileInfo(String id, String name, String email, String phone, Vector<String> availability){
        profileDAO.createProfileInfo(id, name, email, phone, availability);
    }

    public Vector<Object> fetchProfileInfo(String id){
        Profile p = profileDAO.fetchProfileInfo(id);
        Vector<Object> v = new Vector<>();

        if(p == null){
            return v;
        } else {
            Vector<String> s = p.getAvailability().getTimes();
            v.add(p.getName());
            v.add(p.getEmail());
            v.add(p.getPhoneNumber());
            v.add(s);
        }

        return v;
    }

    public void createCourse(String professor, Integer section, String courseCode, Vector<String> students){
        courseDAO.createCourse(professor, section, courseCode, students);
    }


    public void createStudent(String id, String username, String password, Vector<String> courses, Vector<String> tutors) {
        studentDAO.createStudent(id, username, password, courses, tutors);
    }

    // return vector of relevant student info, put username first please :)
    public Vector<Object> fetchStudent(String id) {
        Student s = studentDAO.fetchStudent(id);


        return new Vector<>(1);
    }

    public Vector<ArrayList<String>> getClassmates(String courseId){
        ArrayList<String> students = courseDAO.getStudents(courseId);
        return profileDAO.getClassmates(students);
    }

    // generates dummy data in course collection and profile collection for testing classmates FIXME: remove when done testing
    public void generate(){
        courseDAO.generate();
        profileDAO.generate();
        tutorOfferDAO.generate();
    }

    public boolean verifyAccount(String email){
        return forgotPasswordDAO.verifyAccount(email);
    }

    public Vector<ArrayList<String>> getTutorOffers(String courseId){
        return TutorOfferDAO.getTutorOffers(courseId);
    }
}
