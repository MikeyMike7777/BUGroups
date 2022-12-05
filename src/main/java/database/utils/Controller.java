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

    public void registerStudent(String username, String password, String name, String email, String phone){
        sService.registerStudent(username, password, name, email, phone);
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

    public boolean verifyAccount(String email) {
        return sService.verifyAccount(email);
    }

    public boolean sendPasswordReset(String email) {
        return sService.sendPasswordReset(email);
    }

    public boolean changePassword(String ID, String password){
        return sService.changePassword(ID, password);
    }



}
