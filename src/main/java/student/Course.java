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
}
