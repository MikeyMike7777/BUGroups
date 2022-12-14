package database.student;

import database.utils.BUGUtils;
import database.utils.EmbeddedEmailUtil;
import ui.general.Window;


import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.Vector;

public class StudentService {

    StudentDAO studentDAO = new StudentDAO();
    CourseDAO  courseDAO = new CourseDAO();
    ProfileDAO profileDAO = new ProfileDAO();
    TutorOfferDAO tutorOfferDAO = new TutorOfferDAO();
    ForgotPasswordDAO forgotPasswordDAO = new ForgotPasswordDAO();

    BugDAO bugDAO = new BugDAO();

        String configFilePath = "src/main/config.properties";
        FileInputStream propsInput;
        Properties prop;

    {
        try {
            propsInput = new FileInputStream(configFilePath);
            prop = new Properties();
            prop.load(propsInput);
            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * fetches a tutor offer by id and returns a tutoroffer object
     * @param id the id to search the database for
     * @return the TutorOffer object representing the data of id
     */
    public String fetchTutorOffer (String id){
        TutorOffer t = tutorOfferDAO.fetchTutorOfferInfo(id);
        String s = t.getCourseCode();
        return s;
    }

    /**
     * creates a profile with the specified info
     * @param id the id of the profile entry
     * @param name the name of the profile
     * @param email the email of the profile
     * @param phone the phone number of the profile
     * @param availability the availability of the entry
     */
    public void createProfileInfo(String id, String name, String email, String phone, Vector<String> availability){
        profileDAO.createProfileInfo(id, name, email, phone, availability);
    }

    /**
     * deletes a profile of the specified id
     * @param id the id to remove from the database
     */
    public void deleteProfileInfo(String id){
        profileDAO.deleteProfileInfo(id);
    }

    /**
     * updates a profile that matches id with the updated name
     * @param id the id to search for
     * @param name the new string name to update profile with
     */
    public void updateProfileName(String id, String name){
        profileDAO.updateProfileName(id, name);
    }

    /**
     * updates profile phone number that matches id
     * @param id the id to match
     * @param number the new phone number to update profile with
     */
    public void updateProfilePhoneNumber(String id, String number){
        profileDAO.updateProfilePhoneNumber(id, number);
    }

    /**
     * Fetches the info of the profile that
     * matches id and returns it as a profile object
     * @param id the id to query for
     * @return the profile object with data for id
     */
    public Vector<Object> fetchProfileInfo(String id){
        return profileDAO.fetchProfileInfo(id);
    }

    /**
     * Creates a bug report in entry in the database
     * @param report the message of the bug report
     */
    public void reportBug(String report){
        bugDAO.reportBug(report);
    }

    /**
     * Creates a course entry in the database
     * @param id the id of the course
     * @param courseCode the course code
     * @param section the section of the course
     * @param professor the professor name
     */
    public void addCourse(String id, String username, String courseCode, String section, String professor){ // where id is courseID
        //String sec = section < 10 ? "0" + section : section.toString();
        //String courseId = courseCode.toUpperCase().replaceAll(" ", "") + section; // this is the same as id param

        if (!courseDAO.fetchCourse(id)) // if course does not exist
            courseDAO.createCourse(id, courseCode, section, professor);
        courseDAO.enroll(username, id); // course gets student added to its list of students in it
        studentDAO.addClass(username, id); // student gets course added to the list of courses they're taking
    }

    /**
     * registers a new student account
     * @param username the username of the account
     * @param password the password of the account
     * @return boolean representing whether creating the student was successful or not
     */
    public boolean registerStudent(String username, String password) {
        return studentDAO.registerStudent(username, password);
    }

    /**
     * Fetches a students account information that matches id and returns as a vector of objects
     * @param id the id to search for
     * @return the vector of objects representing the information of id
     */
    public Vector<Object> fetchStudent(String id) {
        Student s = studentDAO.fetchStudent(id);
        Vector<Object> v = new Vector<>();

        if(s == null){
            return v;
        } else {
            v.add(s.getUsername());
            v.add(s.getPassword());
            v.add(s.getCourses());
            v.add(s.getTutorOffers());
        }

        return v;
    }

    /**
     * Gets a students account information that matches id and returns as a student object
     * @param id the id to search for
     * @return the student object representing the information of id
     */
    public Student getStudent(String id) {
        Student s = studentDAO.fetchStudent(id);
        return s;
    }
/**
 * get the information of the classmates in students
 * @param students the students whose information will be gotten
 * @return the vector of students information in the form of ArrayList<String>
 */

    /**
     * get the information of the classmates in specific course
     * @param courseId the course id to search
     * @param username the username of the person searching
     * @return a list of all the students information that isnt username
     */
    public Vector<ArrayList<String>> getClassmates(String courseId, String username){
        ArrayList<String> students = courseDAO.getStudents(courseId, username); // get all the students in the given course
        return profileDAO.getClassmates(students); // get the students' info (availability, name, number, etc.)
    }

    /**
     * Deletes an account that matches id
     * @param id the id to delete from database
     * @return the boolean representing whether the delete was successful or not
     */
    public boolean deleteAccount(String id) {
        return studentDAO.deleteAccount(id);
    }

    /**
     * Deletes an account that matches email
     * @param email the email to delete from database
     * @return the boolean representing whether the delete was successful or not
     */
    public boolean deleteAccountByEmail(String email) {
        return studentDAO.deleteAccountByEmail(email);
    }

    /**
     * verify if an account of type email is already in the database
     * @param email the email to search for
     * @return boolean representing if the user already exists or not
     */
    public boolean verifyAccount(String email){
        return forgotPasswordDAO.verifyAccount(email);
    }

    /**
     * changes the password of the account represented by ID to new password
     * @param ID the id to search for
     * @param password the password to change it to
     * @return the boolean representing whether it changed the password or not
     */
    public boolean changePassword(String ID, String password){
        return studentDAO.changePassword(ID, password);
    }

    /**
     * Fetches a students profile information that matches id and returns as a student object
     * @param id the id to search for
     * @return the profile object representing the information of id
     */
    public Profile getProfile(String id) {
        return studentDAO.fetchProfile(id);
    }

    /**
     * Sends a new email for a password reset
     * @param email the email to send it too
     * @return the boolean representing whether the email send was successful
     */
    public boolean sendPasswordReset(String email){
        if(!email.endsWith("@baylor.edu") || !verifyAccount(email)){
            return false;
        }

        String host = "smtp.gmail.com";
        String port = "465";
        String mailFrom = "info.bugroups@gmail.com";
        String password = prop.getProperty("APP_KEY");
        String newPassword = generatePassword(8);

        // message info
        String mailTo = email;
        String subject = "BUGroups Password Reset";
        StringBuffer body
                = new StringBuffer("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <meta name=\"color-scheme\" content=\"light dark\" />\n" +
                "    <meta name=\"supported-color-schemes\" content=\"light dark\" />\n" +
                "    <title></title>\n" +
                "    <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
                "        /* Base ------------------------------ */\n" +
                "\n" +
                "        @import url(\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap\");\n" +
                "        body {\n" +
                "            width: 100% !important;\n" +
                "            height: 100%;\n" +
                "            margin: 0;\n" +
                "            -webkit-text-size-adjust: none;\n" +
                "        }\n" +
                "\n" +
                "        a {\n" +
                "            color: #3869D4;\n" +
                "        }\n" +
                "\n" +
                "        a img {\n" +
                "            border: none;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            word-break: break-word;\n" +
                "        }\n" +
                "\n" +
                "        .preheader {\n" +
                "            display: none !important;\n" +
                "            visibility: hidden;\n" +
                "            mso-hide: all;\n" +
                "            font-size: 1px;\n" +
                "            line-height: 1px;\n" +
                "            max-height: 0;\n" +
                "            max-width: 0;\n" +
                "            opacity: 0;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "        /* Type ------------------------------ */\n" +
                "\n" +
                "        body,\n" +
                "        td,\n" +
                "        th {\n" +
                "            font-family: \"Nunito Sans\", Helvetica, Arial, sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            margin-top: 0;\n" +
                "            color: #333333;\n" +
                "            font-size: 22px;\n" +
                "            font-weight: bold;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        h2 {\n" +
                "            margin-top: 0;\n" +
                "            color: #333333;\n" +
                "            font-size: 16px;\n" +
                "            font-weight: bold;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        h3 {\n" +
                "            margin-top: 0;\n" +
                "            color: #333333;\n" +
                "            font-size: 14px;\n" +
                "            font-weight: bold;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        td,\n" +
                "        th {\n" +
                "            font-size: 16px;\n" +
                "        }\n" +
                "\n" +
                "        p,\n" +
                "        ul,\n" +
                "        ol,\n" +
                "        blockquote {\n" +
                "            margin: .4em 0 1.1875em;\n" +
                "            font-size: 16px;\n" +
                "            line-height: 1.625;\n" +
                "        }\n" +
                "\n" +
                "        p.sub {\n" +
                "            font-size: 13px;\n" +
                "        }\n" +
                "        /* Utilities ------------------------------ */\n" +
                "\n" +
                "        .align-right {\n" +
                "            text-align: right;\n" +
                "        }\n" +
                "\n" +
                "        .align-left {\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        .align-center {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .u-margin-bottom-none {\n" +
                "            margin-bottom: 0;\n" +
                "        }\n" +
                "        /* Buttons ------------------------------ */\n" +
                "\n" +
                "        .button {\n" +
                "            background-color: #3869D4;\n" +
                "            border-top: 10px solid #3869D4;\n" +
                "            border-right: 18px solid #3869D4;\n" +
                "            border-bottom: 10px solid #3869D4;\n" +
                "            border-left: 18px solid #3869D4;\n" +
                "            display: inline-block;\n" +
                "            color: #FFF;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 3px;\n" +
                "            box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16);\n" +
                "            -webkit-text-size-adjust: none;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        .button--green {\n" +
                "            background-color: #22BC66;\n" +
                "            border-top: 10px solid #22BC66;\n" +
                "            border-right: 18px solid #22BC66;\n" +
                "            border-bottom: 10px solid #22BC66;\n" +
                "            border-left: 18px solid #22BC66;\n" +
                "        }\n" +
                "\n" +
                "        .button--red {\n" +
                "            background-color: #FF6136;\n" +
                "            border-top: 10px solid #FF6136;\n" +
                "            border-right: 18px solid #FF6136;\n" +
                "            border-bottom: 10px solid #FF6136;\n" +
                "            border-left: 18px solid #FF6136;\n" +
                "        }\n" +
                "\n" +
                "        @media only screen and (max-width: 500px) {\n" +
                "            .button {\n" +
                "                width: 100% !important;\n" +
                "                text-align: center !important;\n" +
                "            }\n" +
                "        }\n" +
                "        /* Attribute list ------------------------------ */\n" +
                "\n" +
                "        .attributes {\n" +
                "            margin: 0 0 21px;\n" +
                "        }\n" +
                "\n" +
                "        .attributes_content {\n" +
                "            background-color: #F4F4F7;\n" +
                "            padding: 16px;\n" +
                "        }\n" +
                "\n" +
                "        .attributes_item {\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        /* Related Items ------------------------------ */\n" +
                "\n" +
                "        .related {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 25px 0 0 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .related_item {\n" +
                "            padding: 10px 0;\n" +
                "            color: #CBCCCF;\n" +
                "            font-size: 15px;\n" +
                "            line-height: 18px;\n" +
                "        }\n" +
                "\n" +
                "        .related_item-title {\n" +
                "            display: block;\n" +
                "            margin: .5em 0 0;\n" +
                "        }\n" +
                "\n" +
                "        .related_item-thumb {\n" +
                "            display: block;\n" +
                "            padding-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .related_heading {\n" +
                "            border-top: 1px solid #CBCCCF;\n" +
                "            text-align: center;\n" +
                "            padding: 25px 0 10px;\n" +
                "        }\n" +
                "        /* Discount Code ------------------------------ */\n" +
                "\n" +
                "        .discount {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 24px;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            background-color: #F4F4F7;\n" +
                "            border: 2px dashed #CBCCCF;\n" +
                "        }\n" +
                "\n" +
                "        .discount_heading {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .discount_body {\n" +
                "            text-align: center;\n" +
                "            font-size: 15px;\n" +
                "        }\n" +
                "        /* Social Icons ------------------------------ */\n" +
                "\n" +
                "        .social {\n" +
                "            width: auto;\n" +
                "        }\n" +
                "\n" +
                "        .social td {\n" +
                "            padding: 0;\n" +
                "            width: auto;\n" +
                "        }\n" +
                "\n" +
                "        .social_icon {\n" +
                "            height: 20px;\n" +
                "            margin: 0 8px 10px 8px;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        /* Data table ------------------------------ */\n" +
                "\n" +
                "        .purchase {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 35px 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_content {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 25px 0 0 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_item {\n" +
                "            padding: 10px 0;\n" +
                "            color: #51545E;\n" +
                "            font-size: 15px;\n" +
                "            line-height: 18px;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_heading {\n" +
                "            padding-bottom: 8px;\n" +
                "            border-bottom: 1px solid #EAEAEC;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_heading p {\n" +
                "            margin: 0;\n" +
                "            color: #85878E;\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_footer {\n" +
                "            padding-top: 15px;\n" +
                "            border-top: 1px solid #EAEAEC;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_total {\n" +
                "            margin: 0;\n" +
                "            text-align: right;\n" +
                "            font-weight: bold;\n" +
                "            color: #333333;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_total--label {\n" +
                "            padding: 0 15px 0 0;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            background-color: #F2F4F6;\n" +
                "            color: #51545E;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            color: #51545E;\n" +
                "        }\n" +
                "\n" +
                "        .email-wrapper {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            background-color: #F2F4F6;\n" +
                "        }\n" +
                "\n" +
                "        .email-content {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "        /* Masthead ----------------------- */\n" +
                "\n" +
                "        .email-masthead {\n" +
                "            padding: 25px 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .email-masthead_logo {\n" +
                "            width: 94px;\n" +
                "        }\n" +
                "\n" +
                "        .email-masthead_name {\n" +
                "            font-size: 16px;\n" +
                "            font-weight: bold;\n" +
                "            color: #A8AAAF;\n" +
                "            text-decoration: none;\n" +
                "            text-shadow: 0 1px 0 white;\n" +
                "        }\n" +
                "        /* Body ------------------------------ */\n" +
                "\n" +
                "        .email-body {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .email-body_inner {\n" +
                "            width: 570px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 570px;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            background-color: #FFFFFF;\n" +
                "        }\n" +
                "\n" +
                "        .email-footer {\n" +
                "            width: 570px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 570px;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .email-footer p {\n" +
                "            color: #A8AAAF;\n" +
                "        }\n" +
                "\n" +
                "        .body-action {\n" +
                "            width: 100%;\n" +
                "            margin: 30px auto;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .body-sub {\n" +
                "            margin-top: 25px;\n" +
                "            padding-top: 25px;\n" +
                "            border-top: 1px solid #EAEAEC;\n" +
                "        }\n" +
                "\n" +
                "        .content-cell {\n" +
                "            padding: 45px;\n" +
                "        }\n" +
                "        /*Media Queries ------------------------------ */\n" +
                "\n" +
                "        @media only screen and (max-width: 600px) {\n" +
                "            .email-body_inner,\n" +
                "            .email-footer {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        @media (prefers-color-scheme: dark) {\n" +
                "            body,\n" +
                "            .email-body,\n" +
                "            .email-body_inner,\n" +
                "            .email-content,\n" +
                "            .email-wrapper,\n" +
                "            .email-masthead,\n" +
                "            .email-footer {\n" +
                "                background-color: #333333 !important;\n" +
                "                color: #FFF !important;\n" +
                "            }\n" +
                "            p,\n" +
                "            ul,\n" +
                "            ol,\n" +
                "            blockquote,\n" +
                "            h1,\n" +
                "            h2,\n" +
                "            h3,\n" +
                "            span,\n" +
                "            .purchase_item {\n" +
                "                color: #FFF !important;\n" +
                "            }\n" +
                "            .attributes_content,\n" +
                "            .discount {\n" +
                "                background-color: #222 !important;\n" +
                "            }\n" +
                "            .email-masthead_name {\n" +
                "                text-shadow: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        :root {\n" +
                "            color-scheme: light dark;\n" +
                "            supported-color-schemes: light dark;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <!--[if mso]>\n" +
                "    <style type=\"text/css\">\n" +
                "        .f-fallback  {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <![endif]-->\n" +
                "</head>\n" +
                "<body>\n" +
                "<span class=\"preheader\">Your password has been reset!</span>\n" +
                "<table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "    <tr>\n" +
                "        <td align=\"center\">\n" +
                "            <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                <tr>\n" +
                "                    <td class=\"email-masthead\">\n" +
                "                        <a href=\"https://example.com\" class=\"f-fallback email-masthead_name\">\n" +
                "                            BUGroups\n" +
                "                        </a>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <!-- Email Body -->\n" +
                "                <tr>\n" +
                "                    <td class=\"email-body\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                        <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                            <!-- Body content -->\n" +
                "                            <tr>\n" +
                "                                <td class=\"content-cell\">\n" +
                "                                    <div class=\"f-fallback\">\n" +
                "                                        <h1>Hello,</h1>\n" +
                "                                        <p>You recently requested to reset your password for your BUGroups account. Use the temporary password to login to you account and update your password!</p>\n" +
                "                                        <!-- Action -->\n" +
                "                                        <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                                            <tr>\n" +
                "                                                <td align=\"center\">\n" +
                "                                                    <!-- Border based button\n" +
                "                                 https://litmus.com/blog/a-guide-to-bulletproof-buttons-in-email-design -->\n" +
                "                                                    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "                                                        <tr>\n" +
                "                                                            <p>Your Temporary Password:</p><br><h1>\n" + newPassword +
                "                                                            </h1>\n" +
                "                                                        </tr>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        <p>If you have questions contact BUGroups Support at <b>830-730-7120</b>.</p>\n" +
                "                                        <p>Thanks,\n" +
                "                                            <br>The BUGroups Team</p>\n" +
                "                                    </div>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"email-footer\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                            <tr>\n" +
                "                                <td class=\"content-cell\" align=\"center\">\n" +
                "                                    <p class=\"f-fallback sub align-center\">\n");
                body.append("<img src=\"cid:image1\" width=\"60%\" height=\"40%\" /><br>");
                body.append(
                "                                        <br>1311 S 5th St, Waco, TX 76706\n" +
                "                                    </p>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>\n");



        // inline images
        Map<String, String> inlineImages = new HashMap<String, String>();
        inlineImages.put("image1", "src/main/resources/BUGroups.png");

        BUGUtils.controller.changePassword((email.substring(0, email.length() - 11)), newPassword);

        try {
            EmbeddedEmailUtil.send(host, port, mailFrom, password, mailTo, subject, body.toString(), null);
            System.out.println("Email sent.");
            return true;
        } catch (Exception ex) {
            System.out.println("There was an error sending the email.");
            ex.printStackTrace();
            return false;
        }
    }

    //Sends a password request email to target email and returns boolean of valid send or not
    public boolean sendRegisterEmail(String email, String generatedPassword, String name){
        if(!email.endsWith("@baylor.edu")){
            return false;
        }

        String host = "smtp.gmail.com";
        String port = "465";
        String mailFrom = "info.bugroups@gmail.com";
        String password = prop.getProperty("APP_KEY");

        // message info
        String mailTo = email;
        String subject = "BUGroups Account Activation";
        StringBuffer body
                = new StringBuffer("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <meta name=\"color-scheme\" content=\"light dark\" />\n" +
                "    <meta name=\"supported-color-schemes\" content=\"light dark\" />\n" +
                "    <title></title>\n" +
                "    <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
                "        /* Base ------------------------------ */\n" +
                "\n" +
                "        @import url(\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap\");\n" +
                "        body {\n" +
                "            width: 100% !important;\n" +
                "            height: 100%;\n" +
                "            margin: 0;\n" +
                "            -webkit-text-size-adjust: none;\n" +
                "        }\n" +
                "\n" +
                "        a {\n" +
                "            color: #3869D4;\n" +
                "        }\n" +
                "\n" +
                "        a img {\n" +
                "            border: none;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            word-break: break-word;\n" +
                "        }\n" +
                "\n" +
                "        .preheader {\n" +
                "            display: none !important;\n" +
                "            visibility: hidden;\n" +
                "            mso-hide: all;\n" +
                "            font-size: 1px;\n" +
                "            line-height: 1px;\n" +
                "            max-height: 0;\n" +
                "            max-width: 0;\n" +
                "            opacity: 0;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "        /* Type ------------------------------ */\n" +
                "\n" +
                "        body,\n" +
                "        td,\n" +
                "        th {\n" +
                "            font-family: \"Nunito Sans\", Helvetica, Arial, sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            margin-top: 0;\n" +
                "            color: #333333;\n" +
                "            font-size: 22px;\n" +
                "            font-weight: bold;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        h2 {\n" +
                "            margin-top: 0;\n" +
                "            color: #333333;\n" +
                "            font-size: 16px;\n" +
                "            font-weight: bold;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        h3 {\n" +
                "            margin-top: 0;\n" +
                "            color: #333333;\n" +
                "            font-size: 14px;\n" +
                "            font-weight: bold;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        td,\n" +
                "        th {\n" +
                "            font-size: 16px;\n" +
                "        }\n" +
                "\n" +
                "        p,\n" +
                "        ul,\n" +
                "        ol,\n" +
                "        blockquote {\n" +
                "            margin: .4em 0 1.1875em;\n" +
                "            font-size: 16px;\n" +
                "            line-height: 1.625;\n" +
                "        }\n" +
                "\n" +
                "        p.sub {\n" +
                "            font-size: 13px;\n" +
                "        }\n" +
                "        /* Utilities ------------------------------ */\n" +
                "\n" +
                "        .align-right {\n" +
                "            text-align: right;\n" +
                "        }\n" +
                "\n" +
                "        .align-left {\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        .align-center {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .u-margin-bottom-none {\n" +
                "            margin-bottom: 0;\n" +
                "        }\n" +
                "        /* Buttons ------------------------------ */\n" +
                "\n" +
                "        .button {\n" +
                "            background-color: #3869D4;\n" +
                "            border-top: 10px solid #3869D4;\n" +
                "            border-right: 18px solid #3869D4;\n" +
                "            border-bottom: 10px solid #3869D4;\n" +
                "            border-left: 18px solid #3869D4;\n" +
                "            display: inline-block;\n" +
                "            color: #FFF;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 3px;\n" +
                "            box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16);\n" +
                "            -webkit-text-size-adjust: none;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        .button--green {\n" +
                "            background-color: #22BC66;\n" +
                "            border-top: 10px solid #22BC66;\n" +
                "            border-right: 18px solid #22BC66;\n" +
                "            border-bottom: 10px solid #22BC66;\n" +
                "            border-left: 18px solid #22BC66;\n" +
                "        }\n" +
                "\n" +
                "        .button--red {\n" +
                "            background-color: #FF6136;\n" +
                "            border-top: 10px solid #FF6136;\n" +
                "            border-right: 18px solid #FF6136;\n" +
                "            border-bottom: 10px solid #FF6136;\n" +
                "            border-left: 18px solid #FF6136;\n" +
                "        }\n" +
                "\n" +
                "        @media only screen and (max-width: 500px) {\n" +
                "            .button {\n" +
                "                width: 100% !important;\n" +
                "                text-align: center !important;\n" +
                "            }\n" +
                "        }\n" +
                "        /* Attribute list ------------------------------ */\n" +
                "\n" +
                "        .attributes {\n" +
                "            margin: 0 0 21px;\n" +
                "        }\n" +
                "\n" +
                "        .attributes_content {\n" +
                "            background-color: #F4F4F7;\n" +
                "            padding: 16px;\n" +
                "        }\n" +
                "\n" +
                "        .attributes_item {\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        /* Related Items ------------------------------ */\n" +
                "\n" +
                "        .related {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 25px 0 0 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .related_item {\n" +
                "            padding: 10px 0;\n" +
                "            color: #CBCCCF;\n" +
                "            font-size: 15px;\n" +
                "            line-height: 18px;\n" +
                "        }\n" +
                "\n" +
                "        .related_item-title {\n" +
                "            display: block;\n" +
                "            margin: .5em 0 0;\n" +
                "        }\n" +
                "\n" +
                "        .related_item-thumb {\n" +
                "            display: block;\n" +
                "            padding-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .related_heading {\n" +
                "            border-top: 1px solid #CBCCCF;\n" +
                "            text-align: center;\n" +
                "            padding: 25px 0 10px;\n" +
                "        }\n" +
                "        /* Discount Code ------------------------------ */\n" +
                "\n" +
                "        .discount {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 24px;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            background-color: #F4F4F7;\n" +
                "            border: 2px dashed #CBCCCF;\n" +
                "        }\n" +
                "\n" +
                "        .discount_heading {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .discount_body {\n" +
                "            text-align: center;\n" +
                "            font-size: 15px;\n" +
                "        }\n" +
                "        /* Social Icons ------------------------------ */\n" +
                "\n" +
                "        .social {\n" +
                "            width: auto;\n" +
                "        }\n" +
                "\n" +
                "        .social td {\n" +
                "            padding: 0;\n" +
                "            width: auto;\n" +
                "        }\n" +
                "\n" +
                "        .social_icon {\n" +
                "            height: 20px;\n" +
                "            margin: 0 8px 10px 8px;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        /* Data table ------------------------------ */\n" +
                "\n" +
                "        .purchase {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 35px 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_content {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 25px 0 0 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_item {\n" +
                "            padding: 10px 0;\n" +
                "            color: #51545E;\n" +
                "            font-size: 15px;\n" +
                "            line-height: 18px;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_heading {\n" +
                "            padding-bottom: 8px;\n" +
                "            border-bottom: 1px solid #EAEAEC;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_heading p {\n" +
                "            margin: 0;\n" +
                "            color: #85878E;\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_footer {\n" +
                "            padding-top: 15px;\n" +
                "            border-top: 1px solid #EAEAEC;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_total {\n" +
                "            margin: 0;\n" +
                "            text-align: right;\n" +
                "            font-weight: bold;\n" +
                "            color: #333333;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_total--label {\n" +
                "            padding: 0 15px 0 0;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            background-color: #F2F4F6;\n" +
                "            color: #51545E;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            color: #51545E;\n" +
                "        }\n" +
                "\n" +
                "        .email-wrapper {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            background-color: #F2F4F6;\n" +
                "        }\n" +
                "\n" +
                "        .email-content {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "        /* Masthead ----------------------- */\n" +
                "\n" +
                "        .email-masthead {\n" +
                "            padding: 25px 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .email-masthead_logo {\n" +
                "            width: 94px;\n" +
                "        }\n" +
                "\n" +
                "        .email-masthead_name {\n" +
                "            font-size: 16px;\n" +
                "            font-weight: bold;\n" +
                "            color: #A8AAAF;\n" +
                "            text-decoration: none;\n" +
                "            text-shadow: 0 1px 0 white;\n" +
                "        }\n" +
                "        /* Body ------------------------------ */\n" +
                "\n" +
                "        .email-body {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .email-body_inner {\n" +
                "            width: 570px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 570px;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            background-color: #FFFFFF;\n" +
                "        }\n" +
                "\n" +
                "        .email-footer {\n" +
                "            width: 570px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 570px;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .email-footer p {\n" +
                "            color: #A8AAAF;\n" +
                "        }\n" +
                "\n" +
                "        .body-action {\n" +
                "            width: 100%;\n" +
                "            margin: 30px auto;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .body-sub {\n" +
                "            margin-top: 25px;\n" +
                "            padding-top: 25px;\n" +
                "            border-top: 1px solid #EAEAEC;\n" +
                "        }\n" +
                "\n" +
                "        .content-cell {\n" +
                "            padding: 45px;\n" +
                "        }\n" +
                "        /*Media Queries ------------------------------ */\n" +
                "\n" +
                "        @media only screen and (max-width: 600px) {\n" +
                "            .email-body_inner,\n" +
                "            .email-footer {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        @media (prefers-color-scheme: dark) {\n" +
                "            body,\n" +
                "            .email-body,\n" +
                "            .email-body_inner,\n" +
                "            .email-content,\n" +
                "            .email-wrapper,\n" +
                "            .email-masthead,\n" +
                "            .email-footer {\n" +
                "                background-color: #333333 !important;\n" +
                "                color: #FFF !important;\n" +
                "            }\n" +
                "            p,\n" +
                "            ul,\n" +
                "            ol,\n" +
                "            blockquote,\n" +
                "            h1,\n" +
                "            h2,\n" +
                "            h3,\n" +
                "            span,\n" +
                "            .purchase_item {\n" +
                "                color: #FFF !important;\n" +
                "            }\n" +
                "            .attributes_content,\n" +
                "            .discount {\n" +
                "                background-color: #222 !important;\n" +
                "            }\n" +
                "            .email-masthead_name {\n" +
                "                text-shadow: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        :root {\n" +
                "            color-scheme: light dark;\n" +
                "            supported-color-schemes: light dark;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <!--[if mso]>\n" +
                "    <style type=\"text/css\">\n" +
                "        .f-fallback  {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <![endif]-->\n" +
                "</head>\n" +
                "<body>\n" +
                "<span class=\"preheader\">Welcome to BUGroups!</span>\n" +
                "<table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "    <tr>\n" +
                "        <td align=\"center\">\n" +
                "            <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                <tr>\n" +
                "                    <td class=\"email-masthead\">\n" +
                "                        <a href=\"https://docs.google.com/document/d/1PnFPtSEP0n4_UnJt4TQUT-_ZyQGIcV-3LpHYX06O6P4/edit?usp=sharing\" class=\"f-fallback email-masthead_name\">\n" +
                "                            BUGroups\n" +
                "                        </a>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <!-- Email Body -->\n" +
                "                <tr>\n" +
                "                    <td class=\"email-body\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                        <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                            <!-- Body content -->\n" +
                "                            <tr>\n" +
                "                                <td class=\"content-cell\">\n" +
                "                                    <div class=\"f-fallback\">\n" +
                "                                        <h1>Hi " + name +
                "                                        ,\n</h1><p>You recently created a BUGroups account. Use the following login credentials to access your BUGroups account. Welcome to the community!</p>\n" +
                "                                        <!-- Action -->\n" +
                "                                        <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                                            <tr>\n" +
                "                                                <td align=\"center\">\n" +
                "                                                    <!-- Border based button\n" +
                "                                 https://litmus.com/blog/a-guide-to-bulletproof-buttons-in-email-design -->\n" +
                "                                                    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "                                                        <tr>\n<p>Your Username:</p>\n<h1>" + email.substring(0, email.length() - 11) +
                "                                                            </h1><p>Your Temporary Password:</p><br><h1>\n" + generatedPassword +
                "                                                            </h1>\n" +
                "                                                        </tr>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        <p>If you have questions regarding your account contact BUGroups Support at <b>830-730-7120.</b>\n" +
                "                                        <p>Thanks,\n" +
                "                                            <br>The BUGroups Team</p>\n" +
                "                                    </div>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"email-footer\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                            <tr>\n" +
                "                                <td class=\"content-cell\" align=\"center\">\n" +
                "                                    <p class=\"f-fallback sub align-center\">\n");
        body.append("<img src=\"cid:image1\" width=\"60%\" height=\"40%\" /><br>");
        body.append(
                "                                        <br>1311 S 5th St, Waco, TX 76706\n" +
                        "                                    </p>\n" +
                        "                                </td>\n" +
                        "                            </tr>\n" +
                        "                        </table>\n" +
                        "                    </td>\n" +
                        "                </tr>\n" +
                        "            </table>\n" +
                        "        </td>\n" +
                        "    </tr>\n" +
                        "</table>\n" +
                        "</body>\n" +
                        "</html>\n");


//        body.append("The first image is a chart:<br>");
//        body.append("<img src=\"cid:image1\" width=\"30%\" height=\"30%\" /><br>");
//        body.append("The second one is a cube:<br>");
//        body.append("<img src=\"cid:image2\" width=\"15%\" height=\"15%\" /><br>");
//        body.append("End of message.");
//        body.append("</html>");

        // inline images
        Map<String, String> inlineImages = new HashMap<>();
        inlineImages.put("image1", "src/main/resources/BUGroups.png");
        //inlineImages.put("image2", "C:\\Users\\ninja\\Downloads\\SequenceDiagram\\BUGroups\\src\\main\\resources\\BUGroups.png");

        try {

            EmbeddedEmailUtil.send(host, port, mailFrom, password, mailTo,
                    subject, body.toString(), inlineImages);
            System.out.println("Email sent.");
            return true;
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
            return false;
        }
    }

    public static String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        String password = "";

        password += lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password += capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password += specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password += numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i < length ; i++) {
            password += combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }

    public Vector<ArrayList<String>> getTutorOffers(String courseId){
        Vector<ArrayList<String>> v = TutorOfferDAO.getTutorOffers(courseId);
        v.forEach(q -> {
            q.add((String)profileDAO.fetchProfileInfo(q.get(0)).get(0));
            String availability = profileDAO.fetchProfileInfo(q.get(0)).get(3).toString();
            q.add(availability.substring(1, availability.length() -1));
        });
        return v;
    }

    public void changeAvail(String id, Vector<String> avail) {
        profileDAO.updateProfileAvail(id, avail);
    }

    public void addTutorOffer(String username, String courseCode, String professorTaken, String semesterTaken, Double hourlyRate) {
        //Date d = new Date();
        tutorOfferDAO.createTutorOffer(username + courseCode, username, courseCode, professorTaken, semesterTaken, hourlyRate);
        studentDAO.addTutorOffer(username, username + courseCode);
    }

    public ArrayList<String> getStudentCourses(String id){
        return studentDAO.getCourses(id);
    }

    public Vector<String> getStudentTutors(String username) {
        Vector<String> tutorCourses = new Vector<>();
        // get course IDs that a student has (their id is student who made it and date they made it)
        Vector<String> tutorIds = studentDAO.getTutors(username);
        for(String s : tutorIds){
            System.out.println(s);
        }
        // look in tutor DAO for that tutor offer's course
        for (String s : tutorIds){
            tutorCourses.add(tutorOfferDAO.getTutorCourse(s));
        }
        return tutorCourses;
    }

    public void removeCourse(String username, String courseId){
        // removes student from course's list of students that are enrolled in it
        CourseDAO.removeCourse(username, courseId);
        // removes course from students' list of courses they're enrolled in
        StudentDAO.removeCourse(username, courseId);
    }

    public void removeTutoringOffer(String username, String courseCode){
        // remove tutoring offer from tutorOffers
        tutorOfferDAO.removeOffer(username, courseCode);
        // remove offer from students' list of tutoring offers
        studentDAO.removeOffer(username, courseCode);
    }

    public boolean sendActivityEmail(String email, String name){
        if(!email.endsWith("@baylor.edu")){
            return false;
        }

        String host = "smtp.gmail.com";
        String port = "465";
        String mailFrom = "info.bugroups@gmail.com";
        String password = prop.getProperty("APP_KEY");

        // message info
        String mailTo = email;
        String subject = "Happy New Year!";
        StringBuffer body
                = new StringBuffer("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <meta name=\"color-scheme\" content=\"light dark\" />\n" +
                "    <meta name=\"supported-color-schemes\" content=\"light dark\" />\n" +
                "    <title></title>\n" +
                "    <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
                "        /* Base ------------------------------ */\n" +
                "\n" +
                "        @import url(\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,700&display=swap\");\n" +
                "        body {\n" +
                "            width: 100% !important;\n" +
                "            height: 100%;\n" +
                "            margin: 0;\n" +
                "            -webkit-text-size-adjust: none;\n" +
                "        }\n" +
                "\n" +
                "        a {\n" +
                "            color: #3869D4;\n" +
                "        }\n" +
                "\n" +
                "        a img {\n" +
                "            border: none;\n" +
                "        }\n" +
                "\n" +
                "        td {\n" +
                "            word-break: break-word;\n" +
                "        }\n" +
                "\n" +
                "        .preheader {\n" +
                "            display: none !important;\n" +
                "            visibility: hidden;\n" +
                "            mso-hide: all;\n" +
                "            font-size: 1px;\n" +
                "            line-height: 1px;\n" +
                "            max-height: 0;\n" +
                "            max-width: 0;\n" +
                "            opacity: 0;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "        /* Type ------------------------------ */\n" +
                "\n" +
                "        body,\n" +
                "        td,\n" +
                "        th {\n" +
                "            font-family: \"Nunito Sans\", Helvetica, Arial, sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            margin-top: 0;\n" +
                "            color: #333333;\n" +
                "            font-size: 22px;\n" +
                "            font-weight: bold;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        h2 {\n" +
                "            margin-top: 0;\n" +
                "            color: #333333;\n" +
                "            font-size: 16px;\n" +
                "            font-weight: bold;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        h3 {\n" +
                "            margin-top: 0;\n" +
                "            color: #333333;\n" +
                "            font-size: 14px;\n" +
                "            font-weight: bold;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        td,\n" +
                "        th {\n" +
                "            font-size: 16px;\n" +
                "        }\n" +
                "\n" +
                "        p,\n" +
                "        ul,\n" +
                "        ol,\n" +
                "        blockquote {\n" +
                "            margin: .4em 0 1.1875em;\n" +
                "            font-size: 16px;\n" +
                "            line-height: 1.625;\n" +
                "        }\n" +
                "\n" +
                "        p.sub {\n" +
                "            font-size: 13px;\n" +
                "        }\n" +
                "        /* Utilities ------------------------------ */\n" +
                "\n" +
                "        .align-right {\n" +
                "            text-align: right;\n" +
                "        }\n" +
                "\n" +
                "        .align-left {\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "\n" +
                "        .align-center {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .u-margin-bottom-none {\n" +
                "            margin-bottom: 0;\n" +
                "        }\n" +
                "        /* Buttons ------------------------------ */\n" +
                "\n" +
                "        .button {\n" +
                "            background-color: #3869D4;\n" +
                "            border-top: 10px solid #3869D4;\n" +
                "            border-right: 18px solid #3869D4;\n" +
                "            border-bottom: 10px solid #3869D4;\n" +
                "            border-left: 18px solid #3869D4;\n" +
                "            display: inline-block;\n" +
                "            color: #FFF;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 3px;\n" +
                "            box-shadow: 0 2px 3px rgba(0, 0, 0, 0.16);\n" +
                "            -webkit-text-size-adjust: none;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        .button--green {\n" +
                "            background-color: #22BC66;\n" +
                "            border-top: 10px solid #22BC66;\n" +
                "            border-right: 18px solid #22BC66;\n" +
                "            border-bottom: 10px solid #22BC66;\n" +
                "            border-left: 18px solid #22BC66;\n" +
                "        }\n" +
                "\n" +
                "        .button--red {\n" +
                "            background-color: #FF6136;\n" +
                "            border-top: 10px solid #FF6136;\n" +
                "            border-right: 18px solid #FF6136;\n" +
                "            border-bottom: 10px solid #FF6136;\n" +
                "            border-left: 18px solid #FF6136;\n" +
                "        }\n" +
                "\n" +
                "        @media only screen and (max-width: 500px) {\n" +
                "            .button {\n" +
                "                width: 100% !important;\n" +
                "                text-align: center !important;\n" +
                "            }\n" +
                "        }\n" +
                "        /* Attribute list ------------------------------ */\n" +
                "\n" +
                "        .attributes {\n" +
                "            margin: 0 0 21px;\n" +
                "        }\n" +
                "\n" +
                "        .attributes_content {\n" +
                "            background-color: #F4F4F7;\n" +
                "            padding: 16px;\n" +
                "        }\n" +
                "\n" +
                "        .attributes_item {\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        /* Related Items ------------------------------ */\n" +
                "\n" +
                "        .related {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 25px 0 0 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .related_item {\n" +
                "            padding: 10px 0;\n" +
                "            color: #CBCCCF;\n" +
                "            font-size: 15px;\n" +
                "            line-height: 18px;\n" +
                "        }\n" +
                "\n" +
                "        .related_item-title {\n" +
                "            display: block;\n" +
                "            margin: .5em 0 0;\n" +
                "        }\n" +
                "\n" +
                "        .related_item-thumb {\n" +
                "            display: block;\n" +
                "            padding-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .related_heading {\n" +
                "            border-top: 1px solid #CBCCCF;\n" +
                "            text-align: center;\n" +
                "            padding: 25px 0 10px;\n" +
                "        }\n" +
                "        /* Discount Code ------------------------------ */\n" +
                "\n" +
                "        .discount {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 24px;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            background-color: #F4F4F7;\n" +
                "            border: 2px dashed #CBCCCF;\n" +
                "        }\n" +
                "\n" +
                "        .discount_heading {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .discount_body {\n" +
                "            text-align: center;\n" +
                "            font-size: 15px;\n" +
                "        }\n" +
                "        /* Social Icons ------------------------------ */\n" +
                "\n" +
                "        .social {\n" +
                "            width: auto;\n" +
                "        }\n" +
                "\n" +
                "        .social td {\n" +
                "            padding: 0;\n" +
                "            width: auto;\n" +
                "        }\n" +
                "\n" +
                "        .social_icon {\n" +
                "            height: 20px;\n" +
                "            margin: 0 8px 10px 8px;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        /* Data table ------------------------------ */\n" +
                "\n" +
                "        .purchase {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 35px 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_content {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 25px 0 0 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_item {\n" +
                "            padding: 10px 0;\n" +
                "            color: #51545E;\n" +
                "            font-size: 15px;\n" +
                "            line-height: 18px;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_heading {\n" +
                "            padding-bottom: 8px;\n" +
                "            border-bottom: 1px solid #EAEAEC;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_heading p {\n" +
                "            margin: 0;\n" +
                "            color: #85878E;\n" +
                "            font-size: 12px;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_footer {\n" +
                "            padding-top: 15px;\n" +
                "            border-top: 1px solid #EAEAEC;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_total {\n" +
                "            margin: 0;\n" +
                "            text-align: right;\n" +
                "            font-weight: bold;\n" +
                "            color: #333333;\n" +
                "        }\n" +
                "\n" +
                "        .purchase_total--label {\n" +
                "            padding: 0 15px 0 0;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            background-color: #F2F4F6;\n" +
                "            color: #51545E;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            color: #51545E;\n" +
                "        }\n" +
                "\n" +
                "        .email-wrapper {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            background-color: #F2F4F6;\n" +
                "        }\n" +
                "\n" +
                "        .email-content {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "        /* Masthead ----------------------- */\n" +
                "\n" +
                "        .email-masthead {\n" +
                "            padding: 25px 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .email-masthead_logo {\n" +
                "            width: 94px;\n" +
                "        }\n" +
                "\n" +
                "        .email-masthead_name {\n" +
                "            font-size: 16px;\n" +
                "            font-weight: bold;\n" +
                "            color: #A8AAAF;\n" +
                "            text-decoration: none;\n" +
                "            text-shadow: 0 1px 0 white;\n" +
                "        }\n" +
                "        /* Body ------------------------------ */\n" +
                "\n" +
                "        .email-body {\n" +
                "            width: 100%;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "        }\n" +
                "\n" +
                "        .email-body_inner {\n" +
                "            width: 570px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 570px;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            background-color: #FFFFFF;\n" +
                "        }\n" +
                "\n" +
                "        .email-footer {\n" +
                "            width: 570px;\n" +
                "            margin: 0 auto;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 570px;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .email-footer p {\n" +
                "            color: #A8AAAF;\n" +
                "        }\n" +
                "\n" +
                "        .body-action {\n" +
                "            width: 100%;\n" +
                "            margin: 30px auto;\n" +
                "            padding: 0;\n" +
                "            -premailer-width: 100%;\n" +
                "            -premailer-cellpadding: 0;\n" +
                "            -premailer-cellspacing: 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .body-sub {\n" +
                "            margin-top: 25px;\n" +
                "            padding-top: 25px;\n" +
                "            border-top: 1px solid #EAEAEC;\n" +
                "        }\n" +
                "\n" +
                "        .content-cell {\n" +
                "            padding: 45px;\n" +
                "        }\n" +
                "        /*Media Queries ------------------------------ */\n" +
                "\n" +
                "        @media only screen and (max-width: 600px) {\n" +
                "            .email-body_inner,\n" +
                "            .email-footer {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        @media (prefers-color-scheme: dark) {\n" +
                "            body,\n" +
                "            .email-body,\n" +
                "            .email-body_inner,\n" +
                "            .email-content,\n" +
                "            .email-wrapper,\n" +
                "            .email-masthead,\n" +
                "            .email-footer {\n" +
                "                background-color: #333333 !important;\n" +
                "                color: #FFF !important;\n" +
                "            }\n" +
                "            p,\n" +
                "            ul,\n" +
                "            ol,\n" +
                "            blockquote,\n" +
                "            h1,\n" +
                "            h2,\n" +
                "            h3,\n" +
                "            span,\n" +
                "            .purchase_item {\n" +
                "                color: #FFF !important;\n" +
                "            }\n" +
                "            .attributes_content,\n" +
                "            .discount {\n" +
                "                background-color: #222 !important;\n" +
                "            }\n" +
                "            .email-masthead_name {\n" +
                "                text-shadow: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        :root {\n" +
                "            color-scheme: light dark;\n" +
                "            supported-color-schemes: light dark;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <!--[if mso]>\n" +
                "    <style type=\"text/css\">\n" +
                "        .f-fallback  {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <![endif]-->\n" +
                "</head>\n" +
                "<body>\n" +
                "<span class=\"preheader\">Please confirm account activity. </span>\n" +
                "<table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "    <tr>\n" +
                "        <td align=\"center\">\n" +
                "            <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                <tr>\n" +
                "                    <td class=\"email-masthead\">\n" +
                "                        <a href=\"https://docs.google.com/document/d/1PnFPtSEP0n4_UnJt4TQUT-_ZyQGIcV-3LpHYX06O6P4/edit?usp=sharing\" class=\"f-fallback email-masthead_name\">\n" +
                "                            BUGroups\n" +
                "                        </a>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <!-- Email Body -->\n" +
                "                <tr>\n" +
                "                    <td class=\"email-body\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                        <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                            <!-- Body content -->\n" +
                "                            <tr>\n" +
                "                                <td class=\"content-cell\">\n" +
                "                                    <div class=\"f-fallback\">\n" +
                "                                        <h1>Hi " + name +
                "                                        ,\n</h1><p>Happy New Year! Please log in to your BUGroups account and confirm your activity for the coming year!</p>\n" +
                "                                        <p>Thanks,\n" +
                "                                            <br>The BUGroups Team</p>\n" +
                "                                    </div>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>\n" +
                "                        <table class=\"email-footer\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                            <tr>\n" +
                "                                <td class=\"content-cell\" align=\"center\">\n" +
                "                                    <p class=\"f-fallback sub align-center\">\n");
        body.append("<img src=\"cid:image1\" width=\"60%\" height=\"40%\" /><br>");
        body.append(
                "                                        <br>1311 S 5th St, Waco, TX 76706\n" +
                        "                                    </p>\n" +
                        "                                </td>\n" +
                        "                            </tr>\n" +
                        "                        </table>\n" +
                        "                    </td>\n" +
                        "                </tr>\n" +
                        "            </table>\n" +
                        "        </td>\n" +
                        "    </tr>\n" +
                        "</table>\n" +
                        "</body>\n" +
                        "</html>\n");


//        body.append("The first image is a chart:<br>");
//        body.append("<img src=\"cid:image1\" width=\"30%\" height=\"30%\" /><br>");
//        body.append("The second one is a cube:<br>");
//        body.append("<img src=\"cid:image2\" width=\"15%\" height=\"15%\" /><br>");
//        body.append("End of message.");
//        body.append("</html>");

        // inline images
        Map<String, String> inlineImages = new HashMap<>();
        inlineImages.put("image1", "src/main/resources/BUGroups.png");
        //inlineImages.put("image2", "C:\\Users\\ninja\\Downloads\\SequenceDiagram\\BUGroups\\src\\main\\resources\\BUGroups.png");

        try {

            EmbeddedEmailUtil.send(host, port, mailFrom, password, mailTo,
                    subject, body.toString(), inlineImages);
            System.out.println("Email sent.");
            return true;
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
            return false;
        }
    }

    public void confirmActivities() {
        for (String s : studentDAO.getAllStudents())
            sendActivityEmail(s + "@baylor.edu",
                    s.split("_")[0].toUpperCase().charAt(0) +
                            s.split("_")[0].substring(1) + ' ' +
                            s.split("_")[1].toUpperCase().charAt(0) +
                            s.split("_")[1].substring(1)
                    );
        studentDAO.setActivitiesFalse();
    }

    public boolean confirmed() {
        return studentDAO.confirmed();
    }

    public boolean isActive(String user) {
        return studentDAO.isActive(user);
    }

    public void setActive(String user, boolean b) {
        studentDAO.setActive(user, b);
    }
}
