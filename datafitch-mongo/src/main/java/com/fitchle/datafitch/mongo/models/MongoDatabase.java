package com.fitchle.datafitch.mongo.models;

import com.fitchle.datafitch.mongo.DatafitchMongo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MongoDatabase {
    private final String name;
    private final DatafitchMongo mongo;

    public MongoStack collection(String name) {
        return new MongoStack(this.name, name, mongo);
    }
}
