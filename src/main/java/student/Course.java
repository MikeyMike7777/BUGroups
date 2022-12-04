package student;

import java.util.Vector;

public class Course {
    private String professor;
    private Integer section;
    private String courseCode;
    // when a course is added to a student's list, course has to keep track of student too
    private Vector<Student> students;
    private String id = courseCode + section;

    Course(String professor, Integer section, String courseCode, Vector<Student> students) {
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

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vector<Student> getStudents() {
        return students;
    }

    public void setStudents(Vector<Student> students) {
        this.students = students;
    }
}
