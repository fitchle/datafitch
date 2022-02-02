package com.benchion.bendb.benmysql.services;

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
        this.wheres = new HashMap();
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
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException var10) {
                var10.printStackTrace();
            }

        }

    }

    private PreparedStatement build() {
        ArrayList<String> wheresArr = new ArrayList();
        PreparedStatement stmt = null;
        this.wheres.keySet().forEach((k) -> {
            wheresArr.add(k + " = ?");
        });

        try {
            stmt = this.conn.prepareStatement("DELETE FROM " + this.table + " WHERE " + String.join(" AND ", wheresArr));
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return stmt;
    }
}
