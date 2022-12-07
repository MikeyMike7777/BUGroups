package database.student;

import com.mongodb.client.*;
import database.utils.BUGUtils;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.pull;

public class TutorOfferDAO {
    private static MongoCursor<Document> cursor;

    /**
     * Creates a new tutor offer
     * @param id the id of the offer
     * @param username the username of the user offering the tutoring
     * @param courseCode the courseCode of the course the offer is for
     * @param professorTaken the professor taken for the course
     * @param semesterTaken the semester the course was taken during
     * @param hourlyRate the hourly rate of the offer
     */
    void createTutorOffer(String id, String username, String courseCode, String professorTaken, String semesterTaken, Double hourlyRate){
        TutorOffer t = new TutorOffer(id, username, courseCode, professorTaken, semesterTaken, hourlyRate);
        MongoCollection<Document> collection = BUGUtils.database.getCollection("tutorOffers");
        Bson filter = eq("_id", id);

        Document d = toDocument(t);
        if(fetchTutorOfferInfo(t.getId()) == null) {
            collection.insertOne(d);
        } else {
            collection.deleteOne(filter);
            collection.insertOne(d);
        }
    }

    /**
     * fetches a tutor offer by id and returns a tutoroffer object
     * @param id the id to search the database for
     * @return the TutorOffer object representing the data of id
     */
    TutorOffer fetchTutorOfferInfo(String id){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("tutorOffers");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return toTutorOffer(cursor.next());
        else return null;
    }

    /**
     * Converts a tutor offer to a document
     * @param offer the tutor offer to convert
     * @return the document representing the tutor offer
     */
    public static Document toDocument(TutorOffer offer) {
        return new Document("_id", offer.getId())
                .append("username", offer.getUsername())
                .append("courseCode", offer.getCourseCode())
                .append("professorTaken", offer.getProfessorTaken())
                .append("semesterTaken", offer.getSemesterTaken())
                .append("hourlyRate", offer.getHourlyRate());
    }

    /**
     * Converts a document to a tutor offer
     * @param document the document to convert
     * @return the tutor offer representing the document
     */
    static TutorOffer toTutorOffer(Document document) {
        return new TutorOffer(document.getString("_id"),
                document.getString("username"),
                document.getString("courseCode"),
                document.getString("professorTaken"),
                document.getString("semesterTaken"),
                document.getDouble("hourlyRate"));
    }

    /**
     * gets all the tutoring offers for a specific course code
     * @param courseCode the course code to get offers for
     * @return all the students and their information that have tutoring offers
     */
    static Vector<ArrayList<String>> getTutorOffers(String courseCode){
        Vector<ArrayList<String>> tutorOffers = new Vector<>();
        // querying TutorOffers collection
        MongoCollection<Document> tutorOffersCollection = BUGUtils.database.getCollection("tutorOffers");
        Bson filter = eq("courseCode", courseCode);
        cursor = tutorOffersCollection.find(filter).iterator();
        if (cursor.hasNext()){
            TutorOffer t = toTutorOffer(cursor.next());
            ArrayList<String> tutorOfferInfo = new ArrayList<>();

            tutorOfferInfo.add(t.getUsername());
            tutorOfferInfo.add(t.getCourseCode());
            tutorOfferInfo.add(t.getProfessorTaken());
            tutorOfferInfo.add(t.getSemesterTaken());
            tutorOfferInfo.add(t.getHourlyRate().toString());

            tutorOffers.add(tutorOfferInfo);
        }

        return tutorOffers;
    }

    /**
     * gets a tutors course based on a tutorID
     * @param tutorId the id to search the database for
     * @return the course that the tutor offer is for
     */
    String getTutorCourse(String tutorId){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("tutorOffers");
        Bson filter = eq("_id", tutorId);
        Document d = collection.find(filter).first();
        return d.getString("courseCode");
    }

    /**
     * removes a tutoring offer based on a username and courseCode
     * @param username the username to search for
     * @param courseCode the course code to search for
     */
    void removeOffer(String username, String courseCode){
        MongoCollection<Document> tutorOfferCollection = BUGUtils.database.getCollection("tutorOffers");
        Bson filter = and(eq("courseCode", courseCode), eq("username", username));
        tutorOfferCollection.deleteOne(filter);
    }
}
