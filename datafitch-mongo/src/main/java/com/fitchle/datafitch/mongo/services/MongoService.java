package com.fitchle.datafitch.mongo.services;

import com.fitchle.datafitch.mongo.models.MongoStack;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class MongoService {
    protected final MongoStack stack;
}
