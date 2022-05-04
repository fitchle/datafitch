package com.fitchle.datafitch.mongo.services;

import com.fitchle.datafitch.mongo.models.MongoStack;
import com.mongodb.MongoClient;
import org.bson.Document;
import org.bson.conversions.Bson;

public final class MongoReplacer extends MongoService {
    public MongoReplacer(MongoStack stack) {
        super(stack);
    }

    public MongoReplacer replace(String query, String json) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).replaceOne(Document.parse(query), Document.parse(json));
        }
        return this;
    }

    public MongoReplacer replace(Bson query, Document json) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).replaceOne(query, json);
        }
        return this;
    }
}
