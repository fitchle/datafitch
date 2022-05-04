package com.fitchle.datafitch.mongo.services;

import com.fitchle.datafitch.mongo.models.MongoStack;
import com.mongodb.MongoClient;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public final class MongoUpdater extends MongoService {
    private final ArrayList<UpdateResult> results;

    public MongoUpdater(MongoStack stack) {
        super(stack);
        this.results = new ArrayList<>();
    }

    public MongoUpdater update(String query, String update) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            results.add(client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).updateOne(Document.parse(query), Document.parse(update)));
        }

        return this;
    }

    public MongoUpdater update(Bson query, Bson update) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            results.add(client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).updateOne(query, update));
        }

        return this;
    }

    public MongoUpdater updateMany(String query, String update) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            results.add(client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).replaceOne(Document.parse(query), Document.parse(update)));
        }

        return this;
    }

    public MongoUpdater updateMany(Bson query, Bson update) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            results.add(client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).updateMany(query, update));
        }

        return this;
    }

    public ArrayList<UpdateResult> results() {
        return this.results;
    }

    public UpdateResult result(int index) {
        return this.results.get(index);
    }
}
