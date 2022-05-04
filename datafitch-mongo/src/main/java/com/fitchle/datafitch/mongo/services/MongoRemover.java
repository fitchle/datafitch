package com.fitchle.datafitch.mongo.services;

import com.fitchle.datafitch.mongo.models.MongoStack;
import com.mongodb.MongoClient;
import org.bson.Document;
import org.bson.conversions.Bson;

public final class MongoRemover extends MongoService {

    public MongoRemover(MongoStack stack) {
        super(stack);
    }

    public MongoRemover remove(String query) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).deleteOne(Document.parse(query));
        }

        return this;
    }

    public MongoRemover remove(Bson query) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).deleteOne(query);
        }

        return this;
    }

    public MongoRemover removeMany(Bson query) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).deleteMany(query);
        }

        return this;
    }

    public MongoRemover removeMany(String query) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).deleteMany(Document.parse(query));
        }

        return this;
    }
}
