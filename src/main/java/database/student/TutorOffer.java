package database.student;

public class TutorOffer {
    private String course;
    private String professorTaken;
    private String semesterTaken;
    private Double hourlyRate;


    TutorOffer(String course, String professorTaken,
                                 String semesterTaken, Double hourlyRate){
        this.course = course;
        this.professorTaken = professorTaken;
        this.semesterTaken = semesterTaken;
        this.hourlyRate = hourlyRate;
    }




    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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
}
