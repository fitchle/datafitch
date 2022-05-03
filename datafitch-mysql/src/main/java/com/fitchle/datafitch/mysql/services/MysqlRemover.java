package com.fitchle.datafitch.mysql.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void execute() {
        PreparedStatement stmt = this.build();

        try {
            for (int i = 0; i < this.wheres.values().size(); ++i) {
                stmt.setObject(i + 1, this.wheres.values().toArray()[i]);
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
        ArrayList<String> wheresArr = new ArrayList<>();
        PreparedStatement stmt = null;
        this.wheres.keySet().forEach((k) -> wheresArr.add(k + " = ?"));

        try {
            stmt = this.conn.prepareStatement("DELETE FROM " + this.table + " WHERE " + String.join(" AND ", wheresArr));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return stmt;
    }
}
