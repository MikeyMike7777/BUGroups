package database.student;

import com.mongodb.client.*;
import database.utils.BUGUtils;
import database.utils.MongoInit;
import org.bson.Document;
import org.bson.conversions.Bson;
import ui.general.Window;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class TutorOfferDAO {
    private static MongoCursor<Document> cursor;

    void createTutorOffer(String id, String courseCode, String professorTaken, String semesterTaken, Double hourlyRate){
        TutorOffer t = new TutorOffer(id, Window.username, courseCode, professorTaken, semesterTaken, hourlyRate);
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
        ArrayList<String> a = new ArrayList<>();
        return new Document("_id", offer.getId())
                .append("username", offer.getUsername())
                .append("courseCode", offer.getCourseCode())
                .append("professorTaken", offer.getProfessorTaken())
                .append("semesterTaken", offer.getSemesterTaken())
                .append("hourlyRate", offer.getHourlyRate());
    }

    static TutorOffer toTutorOffer(Document document) {
        return new TutorOffer(document.getString("_id"),
                document.getString("username"),
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

            System.out.println(t.getUsername());
            tutorOfferInfo.add(t.getUsername());
            System.out.println(t.getCourseCode());
            tutorOfferInfo.add(t.getCourseCode());
            System.out.println(t.getProfessorTaken());
            tutorOfferInfo.add(t.getProfessorTaken());
            System.out.println(t.getSemesterTaken());
            tutorOfferInfo.add(t.getSemesterTaken());
            System.out.println(t.getHourlyRate().toString());
            tutorOfferInfo.add(t.getHourlyRate().toString());

            tutorOffers.add(tutorOfferInfo);
        }

        // fixme: not calling this ???
//        for (ArrayList<String> ar : tutorOffers){
//            for (String s : ar){
//                System.out.print(s + ", ");
//            }
//            System.out.println();
//        }

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
        createTutorOffer("tutorOffer1", "CSI 3471", "Dr. Cerny", "Fall 2022", 8.50);
        createTutorOffer("tutorOffer2", "WGS 2300", "Dr. Jug", "Fall 2022", 7.25);
    }
}
