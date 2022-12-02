package student;

public class StudentService {

    CourseDAO courseDAO = new CourseDAO();


    public void createCourse(String professor, Integer section, String courseCode){
        courseDAO.createCourse(professor, section, courseCode);
    }


}
