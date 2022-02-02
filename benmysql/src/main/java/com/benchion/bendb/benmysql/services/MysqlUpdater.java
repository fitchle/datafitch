package com.benchion.bendb.benmysql.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public final class MysqlUpdater {
    private final Connection conn;
    private final String table;
    private final HashMap<String, Object> wheres;
    private final HashMap<String, Object> sets;

    public MysqlUpdater(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
        this.wheres = new HashMap();
        this.sets = new HashMap();
    }

    public MysqlUpdater set(String column, Object value) {
        this.sets.put(column, value);
        return this;
    }

    public MysqlUpdater where(String key, Object value) {
        this.wheres.put(key, value);
        return this;
    }

    public int execute() {
        PreparedStatement stmt = this.build();

        try {
            int i;
            for (i = 0; i < this.sets.values().size(); ++i) {
                stmt.setObject(i + 1, this.sets.values().toArray()[i]);
            }

            if (this.wheres.size() > 0) {
                for (i = this.sets.size(); i < this.wheres.values().size() + this.sets.size(); ++i) {
                    stmt.setObject(i + 1, this.wheres.values().toArray()[i - this.sets.size()]);
                }
            }

            i = stmt.executeUpdate();
            return i;
        } catch (SQLException var12) {
            var12.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException var11) {
                var11.printStackTrace();
            }

        }

        return 0;
    }

    private PreparedStatement build() {
        ArrayList<String> wheresArr = new ArrayList();
        ArrayList<String> setsArr = new ArrayList();
        PreparedStatement stmt = null;
        this.wheres.keySet().forEach((k) -> wheresArr.add(k + " = ?"));
        this.sets.keySet().forEach((k) -> setsArr.add(k + " = ?"));

        try {
            stmt = this.conn.prepareStatement("UPDATE " + this.table + " SET " + String.join(",", setsArr) + (wheresArr.size() > 0 ? " WHERE " + String.join(" AND ", wheresArr) : ""));
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return stmt;
    }
}
