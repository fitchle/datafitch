package com.benchion.bendb.benmysql.services;

import com.benchion.bendb.benmysql.result.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public final class MysqlFetcher {
    private final Connection conn;
    private final String table;
    private final ArrayList<String> columns;
    private final HashMap<String, Object> wheres;

    public MysqlFetcher(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
        this.columns = new ArrayList();
        this.wheres = new HashMap();
    }

    public MysqlFetcher select(String column) {
        this.columns.add(column);
        return this;
    }

    public MysqlFetcher where(String key, Object value) {
        this.wheres.put(key, value);
        return this;
    }

    public Result execute() {
        PreparedStatement stmt = this.build();

        try {
            if (this.wheres.size() > 0) {
                for (int i = 0; i < this.wheres.values().size(); ++i) {
                    stmt.setObject(i + 1, this.wheres.values().toArray()[i]);
                }
            }

            ResultSet result = stmt.executeQuery();
            Result r = new Result(result);
            result.close();
            Result var4 = r;
            return var4;
        } catch (SQLException var14) {
            var14.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException var13) {
                    var13.printStackTrace();
                }
            }

        }

        return null;
    }

    private PreparedStatement build() {
        ArrayList<String> whereArr = new ArrayList();
        PreparedStatement stmt = null;
        this.wheres.keySet().forEach((key) -> {
            whereArr.add(key + " = ?");
        });

        try {
            stmt = this.conn.prepareStatement("SELECT " + String.join(", ", this.columns) + " FROM " + this.table + (whereArr.size() > 0 ? " WHERE " + String.join(" AND ", whereArr) : ""));
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return stmt;
    }
}
