package database.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoInit {
    static ConnectionString connectionString = new ConnectionString(
            "mongodb+srv://gouligab:vwZBMKRZ1vQizZ43@dynamic-chat-app.u9l9jli." +
                    "mongodb.net/?retryWrites=true&w=majority"
    );
    static MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    static MongoClient mongoClient = MongoClients.create(settings);
}
