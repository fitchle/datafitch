package com.fitchle.datafitch.mysql.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public final class MysqlInserter {
    private final Connection conn;
    private final String table;
    private final ArrayList<Object> values;

    public MysqlInserter(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
        this.values = new ArrayList<>();
    }

    public MysqlInserter insert(Object... value) {
        this.values.addAll(Arrays.asList(value));
        return this;
    }

    public void execute() throws SQLException {
        try (PreparedStatement stmt = this.build().orElseThrow(SQLException::new)) {
            for (int i = 0; i < this.values.size(); ++i) {
                stmt.setObject(i + 1, this.values.get(i));
            }

            stmt.execute();
        }
    }

    private Optional<PreparedStatement> build() throws SQLException {
            return Optional.ofNullable(this.conn.prepareStatement("INSERT INTO " + this.table + " VALUES (" + String.join(", ", this.convertQuery(this.values)) + ")"));
    }

    private ArrayList<String> convertQuery(Collection<Object> collection) {
        ArrayList<String> str = new ArrayList<>();
        collection.forEach(k -> str.add("?"));
        return str;
    }
}
