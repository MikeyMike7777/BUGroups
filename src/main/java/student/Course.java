package student;

public class Course {
    private String professor;
    private Integer section;
    private String courseCode;

    Course(String professor, Integer section, String courseCode) {
        this.professor = professor;
        this.section = section;
        this.courseCode = courseCode;
    }
}
