package database.utils;

import database.message.MessageService;
import database.student.StudentService;

import java.util.ArrayList;
import java.util.Vector;

// There is a public static controller in the BUGUtils class
// Use the below code to access it in your UI classes
//
// import database.utils.BUGUtils
//
// BUGUtils.controller.(method call)

public class Controller {
    MessageService mService = new MessageService();
    StudentService sService = new StudentService();

    public void createStudent(String id, String username, String password, Vector<String> courses, Vector<String> tutors){
        sService.createStudent(id, username, password, courses, tutors);
    }

    public Vector<Object> fetchStudent(String id){
        return sService.fetchStudent(id);
    }
    public Vector<Object> createMessage(String text, String author,
                              String courseNumber, Integer board, String message) {
        return mService.createMessage(text, author, courseNumber, board, message);
    }

    public Vector<Object> fetchProfileInfo(String id){
        return sService.fetchProfileInfo(id);
    }

    public void createProfileInfo(String id, String name, String email, String phoneNumber, Vector<String> availibility){
        sService.createProfileInfo(id, name, email, phoneNumber, availibility);
    }

    public void deleteMessage(String id) {
        mService.deleteMessage(id);
    }

    public void editRepostMessage(String id, String text) {
        mService.editRepostMessage(id, text);
    }

    public Vector<Object> fetchBoard(Integer messageBoard) {
        return mService.fetchBoard(messageBoard);
    }

    public Vector<ArrayList<String>> getClassmates(String courseId){
        return sService.getClassmates(courseId);
    }

    // generates dummy data (used when testing classmates tab) FIXME: remove when done testing
    public void generate(){
        sService.generate();
    }

    public Vector<ArrayList<String>> getTutorOffers(String courseId){
        System.out.println("Controller");
        return sService.getTutorOffers(courseId);
    }
}
