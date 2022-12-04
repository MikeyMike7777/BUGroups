package ui.controller;

import message.MessageService;
import student.Course;
import student.StudentService;

import java.util.Vector;

// There is a public static controller in the Window class
// Use the below code to access it in your UI classes
//
// import ui.general.Window
//
// Window.controller.(method call)

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

    public void deleteMessage(String id) {
        mService.deleteMessage(id);
    }

    public void editRepostMessage(String id, String text) {
        mService.editRepostMessage(id, text);
    }

    public Vector<Object> fetchBoard(Integer messageBoard) {
        return mService.fetchBoard(messageBoard);
    }

    public Vector<String> getClassmates(String courseCode){
        return sService.getClassmates(courseCode);
    }
}
