package database.student;

import com.mongodb.client.MongoCollection;
import database.utils.BUGUtils;
import org.bson.Document;

import java.util.Vector;

public class BugDAO {

    /**
     * Creates a bug report in entry in the database
     * @param report the message of the bug report
     */
    public void reportBug(String report){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGS");

        Document d = toDocument(report);

        collection.insertOne(d);
    }

    /**
     * Converts a string report to a document object and returns the object
     * @param report
     * @return the document object representing the report
     */
    public static Document toDocument(String report) {
        return new Document("Report", report);
    }
}
