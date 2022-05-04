package com.fitchle.datafitch.mysql.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public final class Result {
    private final HashMap<String, Object> data = new HashMap<>();

    public Result(ResultSet result) throws SQLException {
        int columnCount = result.getMetaData().getColumnCount();

        while (result.next()) {
            for (int i = 1; i <= columnCount; ++i) {
                this.data.put(result.getMetaData().getColumnName(i), result.getObject(i));
            }
        }

    }

    public Optional<Object> get(String column) {
        return Optional.ofNullable(this.data.get(column));
    }
}
