package student;

import java.util.Vector;

public class StudentService {

    StudentDAO studentDAO = new StudentDAO();
    CourseDAO  courseDAO = new CourseDAO();
    ProfileDAO profileDAO = new ProfileDAO();


    public void createProfileInfo(String name, String email, String phone, Availability availability){
        profileDAO.createProfileInfo(name, email, phone, availability);
    }

    public void createCourse(String professor, Integer section, String courseCode){
        courseDAO.createCourse(professor, section, courseCode);
    }


    public void createStudent(String username, String password) {
        studentDAO.createStudent(username, password);
    }

    // return vector of relevant student info, put username first please :)
    public Vector<Object> fetchStudent(String id) {
        return new Vector<>(1);
    }
}
