package com.fitchle.datafitch.mongo.services;

import com.fitchle.datafitch.mongo.models.MongoStack;
import com.mongodb.MongoClient;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public final class MongoFinder extends MongoService {

    public MongoFinder(MongoStack stack) {
        super(stack);
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
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

    public Document findById(Object id) {
        return findOne(new Document("_id", id));
    }

    public List<Document> findManyById(Object id){
        return findMany(new Document("_id", id));
    }
}
