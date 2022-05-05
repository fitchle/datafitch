package com.fitchle.datafitch.mongo.models;

import com.fitchle.datafitch.mongo.DatafitchMongo;
import com.fitchle.datafitch.mongo.services.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.CreateCollectionOptions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.Document;

import java.util.ArrayList;


@Getter
@RequiredArgsConstructor
public final class MongoStack {
    private final String db;
    private final String name;
    private final DatafitchMongo mongo;

    public MongoStack create() {
        try (MongoClient client = mongo.connect()) {
           client.getDatabase(db).createCollection(name);
        }
        return this;
    }

    public MongoStack create(CreateCollectionOptions options) {
        try (MongoClient client = mongo.connect()) {
            client.getDatabase(db).createCollection(name, options);
        }
        return this;
    }

    public void remove() {
        try (MongoClient client = mongo.connect()) {
            client.getDatabase(this.db).getCollection(this.name).drop();
        }
    }

    public boolean isExist() {
        try (MongoClient client = mongo.connect()) {
            return client.getDatabase(this.db).listCollectionNames().into(new ArrayList<>()).contains(this.name);
        }
    }

    public MongoCollection<Document> fetch() {
        try (MongoClient client = mongo.connect()) {
            return client.getDatabase(this.db).getCollection(this.name);
        }
    }

    public MongoFinder finder() {
        return new MongoFinder(this);
    }

    public MongoInserter inserter() {
        return new MongoInserter(this);
    }

    public MongoUpdater updater() {
        return new MongoUpdater(this);
    }

    public MongoReplacer replacer() {
        return new MongoReplacer(this);
    }

    public MongoRemover remover() {
        return new MongoRemover(this);
    }
}
