package database.utils;

import database.message.MessageService;
import database.student.CourseDAO;
import database.student.StudentService;
import ui.general.Window;

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

    public void init() {}

    public void registerStudent(String username, String password, String name, String email, String phone){
        sService.registerStudent(username, password, name, email, phone);
    }

    public Vector<Object> fetchStudent(String id){
        return sService.fetchStudent(id);
    }

    public void updateProfileName(String id, String name){
        sService.updateProfileName(id, name);
    }
    public void updateProfilePhoneNumber(String id, String number) {
        sService.updateProfilePhoneNumber(id, number);
    }

    public Vector<Object> createMessage(String text, String author,
                              String courseNumber, Integer board, String message) {
        return mService.createMessage(text, author, courseNumber, board, message);
    }

    public void addCourse(String id, String username, String courseCode, String section, String professor){
        sService.addCourse(id, username, courseCode, section, professor);
    }

    public void addTutorOffer(String username, String courseCode, String professorTaken, String semesterTaken, Double hourlyRate) {
       sService.addTutorOffer(username, courseCode, professorTaken, semesterTaken, hourlyRate);
    }

    public ArrayList<String> getStudentCourses(String id){
        return sService.getStudentCourses(id);
    }

    public Vector<String> getStudentTutors(String id){
        return sService.getStudentTutors(id);
    }

    public Vector<Object> fetchProfileInfo(String id) {
        return sService.fetchProfileInfo(id);
    }

    public String fetchTutorOfferCourse(String id){
        return sService.fetchTutorOffer(id);
    }

    public Vector<Object> fetchMessages(String username) {
        return mService.fetchMessages(username);
    }

    public void reportBug(String report) {
        sService.reportBug(report);
    }

    public void changeAvail(String id, Vector<String> avail){
        sService.changeAvail(id, avail);
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

    public Vector<ArrayList<String>> getClassmates(String courseId, String username) {
        return sService.getClassmates(courseId, username);
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

    public String generatePassword(int length){
        return StudentService.generatePassword(length);
    }

    public boolean sendRegisterEmail(String email, String generatedPassword, String name){
        sService.sendRegisterEmail(email, generatedPassword, name);
        return true;
    }

    public boolean deleteAccount(String id) {
        return sService.deleteAccount(id);
    }


    public Vector<ArrayList<String>> getTutorOffers(String courseId){
        return sService.getTutorOffers(courseId);
    }

    public void removeCourse(String username, String courseId){
        sService.removeCourse(username, courseId);
    }

    public void removeTutoringOffer(String username, String courseCode){
        sService.removeTutoringOffer(username, courseCode);
    }

}
