package student;

import java.util.Vector;

public class StudentService {

    StudentDAO studentDAO = new StudentDAO();
    CourseDAO  courseDAO = new CourseDAO();
    ProfileDAO profileDAO = new ProfileDAO();


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


    public void createStudent(String username, String password) {
        studentDAO.createStudent(username, password);
    }

    // return vector of relevant student info, put username first please :)
    public Vector<Object> fetchStudent(String id) {
        return new Vector<>(1);
    }

    public Vector<Vector<String>> getClassmates(String courseCode){
        Vector<String> students = courseDAO.getStudents(courseCode);
        return profileDAO.getClassmates(students);
    }

    // generates dummy data in course collection and profile collection for testing classmates FIXME: remove when done testing
    public void generate(){
        courseDAO.generate();
        profileDAO.generate();
    }
}
