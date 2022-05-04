package com.fitchle.datafitch.mongo.services;

import com.fitchle.datafitch.mongo.models.MongoStack;
import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class MongoInserter extends MongoService {

    public MongoInserter(MongoStack stack) {
        super(stack);
    }

    public MongoInserter insert(String json) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).insertOne(Document.parse(json));
        }

        return this;
    }

    public MongoInserter insert(Document document) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).insertOne(document);
        }

        return this;
    }

    public MongoInserter insertMany(Document... document) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).insertMany(Arrays.asList(document));
        }

        return this;
    }

    public MongoInserter insertMany(String... document) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).insertMany(Arrays.stream(document).map(Document::parse).collect(Collectors.toList()));
        }

        return this;
    }
}
