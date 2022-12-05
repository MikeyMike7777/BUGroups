package database.utils;

import com.mongodb.client.MongoDatabase;

public class BUGUtils {
    public final static int APP_WIDTH = 800;
    public final static int APP_HEIGHT = 600;
    public static Controller controller = new Controller();
    public static MongoDatabase database = MongoInit.mongoClient.getDatabase("test");
}
