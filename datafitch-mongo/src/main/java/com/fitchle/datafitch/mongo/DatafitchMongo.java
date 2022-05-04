package com.fitchle.datafitch.mongo;


import com.fitchle.datafitch.mongo.models.MongoDatabase;
import com.mongodb.MongoClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class DatafitchMongo {
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    public MongoClient connect() {
        MongoDatasource datasource = new MongoDatasource(host, port, username, password);
        return datasource.connect();
    }

    public MongoDatabase database(String name) {
        return new MongoDatabase(name, this);
    }
}
