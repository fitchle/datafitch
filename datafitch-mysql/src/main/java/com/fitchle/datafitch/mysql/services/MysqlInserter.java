package com.fitchle.datafitch.mysql.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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

    public void execute() {
        PreparedStatement stmt = this.build();

        try {
            for (int i = 0; i < this.values.size(); ++i) {
                stmt.setObject(i + 1, this.values.get(i));
            }

            stmt.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        }

    }

    private PreparedStatement build() {
        PreparedStatement stmt = null;

        try {
            stmt = this.conn.prepareStatement("INSERT INTO " + this.table + " VALUES (" + String.join(", ", this.convertQuery(this.values)) + ")");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return stmt;
    }

    private ArrayList<String> convertQuery(Collection<Object> collection) {
        ArrayList<String> str = new ArrayList<>();
        collection.forEach((k) -> str.add("?"));
        return str;
    }
}
