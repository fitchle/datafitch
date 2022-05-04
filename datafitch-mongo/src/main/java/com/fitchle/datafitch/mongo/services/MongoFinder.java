package com.fitchle.datafitch.mongo.services;

import com.fitchle.datafitch.mongo.models.MongoStack;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.internal.MongoIterables;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public final class MongoFinder extends MongoService {

    public MongoFinder(MongoStack stack) {
        super(stack);
    }

    public Document findOne(String query) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            return client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).find(Document.parse(query)).cursor().next();
        }
    }

    public Document findOne(Bson query) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            return client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).find(query).cursor().next();
        }
    }

    public List<Document> findMany(String query) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            return toList(client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).find(Document.parse(query)));
        }
    }

    public List<Document> findMany(Bson query) {
        try (MongoClient client = this.stack.getMongo().connect()) {
            return toList(client.getDatabase(this.stack.getDb()).getCollection(this.stack.getName()).find(query));
        }
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
