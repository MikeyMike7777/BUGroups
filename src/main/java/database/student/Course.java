package database.student;

import java.util.Vector;

public class Course {
    private String professor;
    private String section;
    private String courseCode;
    // when a course is added to a student's list, course has to keep track of student too
    private Vector<String> students; // stores the students' IDs from the Student collection
    //private String id = courseCode + section;

    Course(String courseCode, String section, String professor, Vector<String> students) {
        this.professor = professor;
        this.section = section;
        this.courseCode = courseCode;
        this.students = students;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public Vector<String> getStudents() {
        return students;
    }

    public void setStudents(Vector<String> students) {
        this.students = students;
    }
}
