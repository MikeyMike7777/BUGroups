package student;

public class TutorOffer {
    private Course course;
    private String professorTaken;
    private String semesterTaken;
    private Double hourlyRate;


    TutorOffer(Course course, String professorTaken,
                                 String semesterTaken, Double hourlyRate){
        this.course = course;
        this.professorTaken = professorTaken;
        this.semesterTaken = semesterTaken;
        this.hourlyRate = hourlyRate;
    }
}
