package database.student;

import com.mongodb.client.MongoCollection;
import database.utils.BUGUtils;
import org.bson.Document;

import java.util.Vector;

public class BugDAO {

    public void reportBug(String report){
        MongoCollection<Document> collection = BUGUtils.database.getCollection("BUGS");

        Document d = toDocument(report);

        collection.insertOne(d);
    }

    public static Document toDocument(String report) {
        return new Document("Report", report);
    }
}
