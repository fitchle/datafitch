package com.fitchle.datafitch.mysql.services;

import com.fitchle.datafitch.mysql.models.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public final class MysqlFetcher {
    private final Connection conn;
    private final String table;
    private final ArrayList<String> columns;
    private final HashMap<String, Object> wheres;

    public MysqlFetcher(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
        this.columns = new ArrayList<>();
        this.wheres = new HashMap<>();
    }

    public MysqlFetcher select(String column) {
        this.columns.add(column);
        return this;
    }

    public MysqlFetcher where(String key, Object value) {
        this.wheres.put(key, value);
        return this;
    }

    public Result execute() throws SQLException {
        try (PreparedStatement stmt = this.build().orElseThrow(SQLException::new)) {
            try {
                if (this.wheres.size() > 0) {
                    for (int i = 0; i < this.wheres.values().size(); ++i) {
                        stmt.setObject(i + 1, this.wheres.values().toArray()[i]);
                    }
                }

                ResultSet result = stmt.executeQuery();
                Result r = new Result(result);
                result.close();
                return r;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }


        return null;
    }

    private Optional<PreparedStatement> build() throws SQLException {
        ArrayList<String> whereArr = new ArrayList<>();
        this.wheres.keySet().forEach(key -> whereArr.add(key + " = ?"));
        return Optional.of(this.conn.prepareStatement("SELECT " + String.join(", ", this.columns) + " FROM " + this.table + (whereArr.isEmpty() ? " WHERE " + String.join(" AND ", whereArr) : "")));
    }
}
