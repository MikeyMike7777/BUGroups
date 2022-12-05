package database.student;

import com.mongodb.client.*;
import database.utils.BUGUtils;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class TutorOfferDAO {
    private static MongoCursor<Document> cursor;

    void createTutorOffer(String id,String courseCode, String professorTaken, String semesterTaken, Double hourlyRate){
        TutorOffer t = new TutorOffer(id, courseCode, professorTaken, semesterTaken, hourlyRate);
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

    TutorOffer fetchTutorOfferInfo(String id){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("tutorOffers");
        Bson filter = eq("_id", id);
        cursor = collection.find(filter).iterator();
        if (cursor.hasNext())
            return toTutorOffer(cursor.next());
        else return null;
    }

    public static Document toDocument(TutorOffer offer) {
        return new Document("_id", offer.getId())
                .append("courseCode", offer.getCourseCode())
                .append("professorTaken", offer.getProfessorTaken())
                .append("semesterTaken", offer.getSemesterTaken())
                .append("hourlyRate", offer.getHourlyRate());
    }

    static TutorOffer toTutorOffer(Document document) {
        return new TutorOffer(document.getString("_id"),
                document.getString("courseCode"),
                document.getString("professorTaken"),
                document.getString("semesterTaken"),
                document.getDouble("hourlyRate"));
    }

    // find all the tutor offers with the course code
    //
    static Vector<ArrayList<String>> getTutorOffers(String courseCode){
        Vector<ArrayList<String>> tutorOffers = new Vector<>();
        // querying TutorOffers collection
        MongoCollection<Document> tutorOffersCollection = BUGUtils.database.getCollection("TutorOffers");
        Bson filter = eq("courseCode", courseCode);
        cursor = tutorOffersCollection.find(filter).iterator();
        if (cursor.hasNext()){
            TutorOffer t = toTutorOffer(cursor.next());
            ArrayList<String> tutorOfferInfo = new ArrayList<>();

            tutorOfferInfo.add(t.getCourseCode());
            tutorOfferInfo.add(t.getProfessorTaken());
            tutorOfferInfo.add(t.getSemesterTaken());
            tutorOfferInfo.add(t.getHourlyRate().toString());

            tutorOffers.add(tutorOfferInfo);
        }

        return tutorOffers;
    }

    // generates dummy data in tutorOffers collection for testing classmates FIXME: remove when done testing
    // id is courseCode + ??? fixme: do we even need a meaningful courseCode
    void generate(){
        MongoCollection<Document> tutorOfferCollection = BUGUtils.database.getCollection("TutorOffer");
        if (tutorOfferCollection.countDocuments() > 0){
            // if there is stuff in the collection, delete everything
            tutorOfferCollection.deleteMany(new Document());
        }
        createTutorOffer("tutorOffer1", "CSI 3471", "Dr. Cerny", "Fall 2022", 55.50);
        createTutorOffer("tutorOffer2", "WGS 2300", "Dr. Jug", "Spring 2021", 1.00);
    }
}
