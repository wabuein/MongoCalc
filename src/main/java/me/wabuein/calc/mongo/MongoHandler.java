package me.wabuein.calc.mongo;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.SneakyThrows;
import me.wabuein.calc.Calculator;
import me.wabuein.calc.util.Configuration;
import org.bson.Document;

@Getter
public class MongoHandler {

    private final Calculator calculator;

    private MongoDatabase database;
    private MongoClient client;
    private MongoCollection<Document> operations;

    /**
     * Initializes the MongoHandler that manages the Mongo database
     *
     * @param calculator Instance of main class
     */

    public MongoHandler(Calculator calculator) {
        this.calculator = calculator;

        this.init();
    }

    /**
     * Sets up the Mongo Database
     */

    @SneakyThrows
    private void init() {
        final Configuration config = calculator.getConfiguration();

        if (config.getBoolean("mongo.uri-mode")) {
            this.client = MongoClients.create(config.get("mongo.uri.connection-string"));
            this.database = client.getDatabase(config.get("mongo.uri.database"));

            this.loadCollections();
            return;
        }

        boolean auth = config.getBoolean("mongo.normal.auth.enabled");
        String host = config.get("mongo.normal.host");
        int port = config.getInteger("mongo.normal.port");

        String uri = "mongodb://" + host + ":" + port;

        if (auth) {
            String username = config.get("mongo.normal.auth.username");
            String password = config.get("mongo.normal.auth.password");
            uri = "mongodb://" + username + ":" + password + "@" + host + ":" + port;
        }

        this.client = MongoClients.create(uri);
        this.database = client.getDatabase(config.get("mongo.uri.database"));

        this.loadCollections();
    }

    private void loadCollections() {
        this.operations = database.getCollection("operations");
    }

}