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

    /**
     * registers a new student account
     * @param username the username of the account
     * @param password the password of the account
     * @return boolean representing whether creating the student was successful or not
     */
    public void registerStudent(String username, String password, String name, String email, String phone){
        sService.registerStudent(username, password);
    }

    /**
     * Fetches a students account information that matches id and returns as a vector of objects
     * @param id the id to search for
     * @return the vector of objects representing the information of id
     */
    public Vector<Object> fetchStudent(String id){
        return sService.fetchStudent(id);
    }

    /**
     * updates a profile that matches id with the updated name
     * @param id the id to search for
     * @param name the new string name to update profile with
     */
    public void updateProfileName(String id, String name){
        sService.updateProfileName(id, name);
    }

    /**
     * updates profile phone number that matches id
     * @param id the id to match
     * @param number the new phone number to update profile with
     */
    public void updateProfilePhoneNumber(String id, String number) {
        sService.updateProfilePhoneNumber(id, number);
    }

    /**
     * creates a new message in database with the following information
     * @param text text of the message
     * @param author the author of the creator
     * @param courseNumber the course number
     * @param board the board the message is under
     * @param message the message
     * @return the Vector of objects
     */
    public Vector<Object> createMessage(String text, String author,
                              String courseNumber, Integer board, String message) {
        return mService.createMessage(text, author, courseNumber, board, message);
    }

    /**
     * Creates a course entry in the database
     * @param id the id of the course
     * @param courseCode the course code
     * @param section the section of the course
     * @param professor the professor name
     */
    public void addCourse(String id, String username, String courseCode, String section, String professor){
        sService.addCourse(id, username, courseCode, section, professor);
    }

    /**
     * Creates a new tutor offer
     * @param username the username of the user offering the tutoring
     * @param courseCode the courseCode of the course the offer is for
     * @param professorTaken the professor taken for the course
     * @param semesterTaken the semester the course was taken during
     * @param hourlyRate the hourly rate of the offer
     */
    public void addTutorOffer(String username, String courseCode, String professorTaken, String semesterTaken, Double hourlyRate) {
       sService.addTutorOffer(username, courseCode, professorTaken, semesterTaken, hourlyRate);
    }

    /**
     * gets the courses that the username is apart of
     * @param id the id to search courses for
     * @return an arraylist of all the courses this username is in
     */
    public ArrayList<String> getStudentCourses(String id){
        return sService.getStudentCourses(id);
    }

    /**
     * gets the students tutoring student with username
     * @param username the username to search for
     * @return the vector of all students tutoring username
     */
    public Vector<String> getStudentTutors(String username){
        return sService.getStudentTutors(username);
    }

    /**
     * Fetches the info of the profile that
     * matches id and returns it as a profile object
     * @param id the id to query for
     * @return the profile object with data for id
     */
    public Vector<Object> fetchProfileInfo(String id) {
        return sService.fetchProfileInfo(id);
    }

    /**
     * fetches a tutor offer by id and returns a tutoroffer object
     * @param id the id to search the database for
     * @return the TutorOffer object representing the data of id
     */
    public String fetchTutorOfferCourse(String id){
        return sService.fetchTutorOffer(id);
    }

    /**
     * Fetches a list message replies that matches an id from the database
     * @param username the username of the message replies to fetch
     * @return returns a list of replies of the message of id
     */
    public Vector<Object> fetchMessages(String username) {
        return mService.fetchMessages(username);
    }

    /**
     * Creates a bug report in entry in the database
     * @param report the message of the bug report
     */
    public void reportBug(String report) {
        sService.reportBug(report);
    }

    /**
     * updates profile availability that matches id with new availability
     * @param id the id to match
     * @param avail the new availability to update profile with
     */
    public void changeAvail(String id, Vector<String> avail){
        sService.changeAvail(id, avail);
    }


    /**
     * creates a profile with the specified info
     * @param id the id of the profile entry
     * @param name the name of the profile
     * @param email the email of the profile
     * @param phoneNumber the phone number of the profile
     * @param availibility the availability of the entry
     */
    public void createProfileInfo(String id, String name, String email, String phoneNumber, Vector<String> availibility){
        sService.createProfileInfo(id, name, email, phoneNumber, availibility);
    }

    /**
     * deletes a message of id in the database
     * @param id the id that will be used to delete
     */
    public void deleteMessage(String id) {
        mService.deleteMessage(id);
    }

    /**
     * Edits a repost message in the database that matches id
     * @param id the id of the repost message
     * @param text the updated text of the repost message
     */
    public void editRepostMessage(String id, String text) {
        mService.editRepostMessage(id, text);
    }

    /**
     * Fetches a message that matches an id from the database
     * @param messageBoard the id of the message to fetch
     * @return returns a message object representing the message in the database
     */
    public Vector<Object> fetchBoard(Integer messageBoard) {
        return mService.fetchBoard(messageBoard);
    }

    /**
     * get the information of the classmates in specific course
     * @param courseId the course id to search
     * @param username the username of the person searching
     * @return a list of all the students information that isnt username
     */
    public Vector<ArrayList<String>> getClassmates(String courseId, String username) {
        return sService.getClassmates(courseId, username);
    }

    /**
     * verify if an account of type email is already in the database
     * @param email the email to search for
     * @return boolean representing if the user already exists or not
     */
    public boolean verifyAccount(String email) {
        return sService.verifyAccount(email);
    }

    /**
     * Sends a new email for a password reset
     * @param email the email to send it too
     * @return the boolean representing whether the email send was successful
     */
    public boolean sendPasswordReset(String email) {
        return sService.sendPasswordReset(email);
    }

    /**
     * changes the password of the account represented by ID to new password
     * @param ID the id to search for
     * @param password the password to change it to
     * @return the boolean representing whether it changed the password or not
     */
    public boolean changePassword(String ID, String password){
        return sService.changePassword(ID, password);
    }

    /**
     * generates a new unique password of length
     * @param length the length of the unique password
     * @return the newly created password
     */
    public String generatePassword(int length){
        return StudentService.generatePassword(length);
    }

    /**
     * Sends a new email for a registering an account
     * @param email the email to send it too
     * @return the boolean representing whether the email send was successful
     */
    public boolean sendRegisterEmail(String email, String generatedPassword, String name){
        sService.sendRegisterEmail(email, generatedPassword, name);
        return true;
    }

    /**
     * Deletes an account that matches id
     * @param id the id to delete from database
     * @return the boolean representing whether the delete was successful or not
     */
    public boolean deleteAccountByID(String id) {
        return sService.deleteAccount(id);
    }

    /**
     * Deletes an account that matches email
     * @param email the email to delete from database
     * @return the boolean representing whether the delete was successful or not
     */
    public boolean deleteAccountByEmail(String email) {
        return sService.deleteAccountByEmail(email);
    }

    /**
     * verify if an account of type email is already in the database
     * @param courseId the email to search for
     * @return boolean representing if the user already exists or not
     */
    public Vector<ArrayList<String>> getTutorOffers(String courseId){
        return sService.getTutorOffers(courseId);
    }

    /**
     * removes student from course's list of students that are enrolled in it
     * @param username the username of the user to remove
     * @param courseId the id of the course to remove them from
     */
    public void removeCourse(String username, String courseId){
        sService.removeCourse(username, courseId);
    }

    /**
     * removes a tutoring offer based on a username and courseCode
     * @param username the username to search for
     * @param courseCode the course code to search for
     */
    public void removeTutoringOffer(String username, String courseCode){
        sService.removeTutoringOffer(username, courseCode);
    }

}
