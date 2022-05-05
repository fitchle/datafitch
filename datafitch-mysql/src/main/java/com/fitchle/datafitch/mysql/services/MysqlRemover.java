package com.fitchle.datafitch.mysql.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public final class MysqlRemover {
    private final String table;
    private final Connection conn;
    private final HashMap<String, Object> wheres;

    public MysqlRemover(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
        this.wheres = new HashMap<>();
    }

    public MysqlRemover where(String column, Object value) {
        this.wheres.put(column, value);
        return this;
    }

    public void execute() throws SQLException {
        try (PreparedStatement stmt = this.build().orElseThrow(SQLException::new)) {
            for (int i = 0; i < this.wheres.values().size(); ++i) {
                stmt.setObject(i + 1, this.wheres.values().toArray()[i]);
            }

            stmt.execute();
        }
    }

    private Optional<PreparedStatement> build() throws SQLException {
        ArrayList<String> wheresArr = new ArrayList<>();
        this.wheres.keySet().forEach(k -> wheresArr.add(k + " = ?"));
        return Optional.ofNullable(this.conn.prepareStatement("DELETE FROM " + this.table + " WHERE " + String.join(" AND ", wheresArr)));
    }
}
