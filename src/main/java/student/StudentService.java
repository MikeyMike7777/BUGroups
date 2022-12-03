package student;

import java.util.Vector;

public class StudentService {

    StudentDAO studentDAO = new StudentDAO();



    public void createProfileInfo(String name, String email, Availability availability){

    }

    public void createStudent(String username, String password) {
        studentDAO.createStudent(username, password);
    }

    // return vector of relevant student info, put username first please :)
    public Vector<Object> fetchStudent(String id) {
        return new Vector<>(1);
    }
}
