import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import message.MessageService;
import org.bson.Document;
import student.Student;

public class MongoDBUtils {

    public static void main(String [] args) {

        ConnectionString connectionString = new ConnectionString("mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase db = mongoClient.getDatabase("test");

        Student s = new Student();
        MessageService mService = new MessageService();
        mService.createMessage("hello", s, "12/01/2022 17:45:32", "CSI3471", null);

        MongoCollection<Document> messages = db.getCollection("BUGMessages");
        MongoCursor<Document> cursor = messages.find().cursor();

        System.out.println(cursor.next().toJson());

//        for(String str : mongoClient.listDatabaseNames()){
//            System.out.println(str);
//        }
    }

}