package com.fitchle.datafitch.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
final class MongoDatasource {
    private final String host;
    private final int port;
    private final String username, password;

    private MongoClientURI uri;
    private MongoClientOptions options;

    public void build() {
        this.uri = new MongoClientURI("mongodb://" +
                (this.username == null || this.username.isEmpty() ? "" : username)
                + (this.password == null || this.password.isEmpty() ? "" : ":" + password)
                + host + ":" + port
        );
    }

    public MongoClient connect() {
        this.build();
        return new MongoClient(uri);
    }
}
