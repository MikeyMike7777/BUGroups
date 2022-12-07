package database.student;

public class TutorOffer {
    private String id;
    private String username;
    private String courseCode;
    private String professorTaken;
    private String semesterTaken;
    private Double hourlyRate;

    TutorOffer(String id, String username, String courseCode, String professorTaken,
                                 String semesterTaken, Double hourlyRate) {
        this.id = id;
        this.username = username;
        this.courseCode = courseCode;
        this.professorTaken = professorTaken;
        this.semesterTaken = semesterTaken;
        this.hourlyRate = hourlyRate;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getProfessorTaken() {
        return professorTaken;
    }

    public void setProfessorTaken(String professorTaken) {
        this.professorTaken = professorTaken;
    }

    public String getSemesterTaken() {
        return semesterTaken;
    }

    public void setSemesterTaken(String semesterTaken) {
        this.semesterTaken = semesterTaken;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getId() {
        return id;
    }

    public String getUsername(){
        return username;
    }
}
