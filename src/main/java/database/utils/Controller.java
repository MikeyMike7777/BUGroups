package database.utils;

import database.message.MessageService;
import database.student.StudentService;

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

    public void createStudent(String username, String password){
        sService.createStudent(username, password);
    }
    public void createMessage(String text, String author,
                              String courseNumber, Integer board, String message) {
        mService.createMessage(text, author, courseNumber, board, message);
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

    public Vector<Vector<String>> getClassmates(String courseId){
        return sService.getClassmates(courseId);
    }

    // generates dummy data (used when testing classmates tab) FIXME: remove when done testing
    public void generate(){
        sService.generate();
    }

}
