package database.utils;

import com.mongodb.client.MongoDatabase;

public class BUGUtils {
    public static Controller controller = new Controller();
    public static MongoDatabase database = MongoInit.mongoClient.getDatabase("test");
}
