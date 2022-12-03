package student;

import java.util.Vector;

public class StudentService {

    CourseDAO courseDAO = new CourseDAO();


    public void createCourse(String professor, Integer section, String courseCode){
        courseDAO.createCourse(professor, section, courseCode);
    }

    // return vector of relevant student info, put username first please :)
    public Vector<Object> fetchStudent(String id) {
        return new Vector<>(1);
    }
}
